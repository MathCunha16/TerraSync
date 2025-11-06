package com.terrasync.backend.service.sensordata.useCases;

import com.terrasync.backend.dto.sensordata.SensorDataResponseDTO;
import com.terrasync.backend.entity.SensorData;
import com.terrasync.backend.mapper.SensorDataMapper;
import com.terrasync.backend.repository.SensorDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FindAllSensorDataBySensorUseCase {

    private final Logger logger = LoggerFactory.getLogger(FindAllSensorDataBySensorUseCase.class);
    private final SensorDataRepository sensorDataRepository;
    private final SensorDataMapper sensorDataMapper;

    public FindAllSensorDataBySensorUseCase(SensorDataRepository sensorDataRepository, SensorDataMapper sensorDataMapper) {
        this.sensorDataRepository = sensorDataRepository;
        this.sensorDataMapper = sensorDataMapper;
    }

    public List<SensorDataResponseDTO> handle(Long sensorId, Long userId) {
        logger.info("--------- Listing all data for sensor ID: {} for user: {} ---------", sensorId, userId);

        // The repository method ensures the user has access to the sensor data
        List<SensorData> sensorDataList = sensorDataRepository.findAllBySensor_IdAndSensor_Crop_Farm_User_Id(sensorId, userId);

        logger.info("--------- Found {} data points for sensor ID: {} ---------", sensorDataList.size(), sensorId);

        return sensorDataMapper.toResponseDTOList(sensorDataList);
    }
}
