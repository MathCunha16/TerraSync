package com.terrasync.backend.service.sensordata.useCases;

import com.terrasync.backend.dto.sensordata.SensorDataRequestDTO;
import com.terrasync.backend.dto.sensordata.SensorDataResponseDTO;
import com.terrasync.backend.entity.Sensor;
import com.terrasync.backend.entity.SensorData;
import com.terrasync.backend.exception.domain.ResourceNotFoundException;
import com.terrasync.backend.mapper.SensorDataMapper;
import com.terrasync.backend.repository.SensorDataRepository;
import com.terrasync.backend.repository.SensorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CreateSensorDataUseCase {

    private final Logger logger = LoggerFactory.getLogger(CreateSensorDataUseCase.class);
    private final SensorDataRepository sensorDataRepository;
    private final SensorRepository sensorRepository;
    private final SensorDataMapper sensorDataMapper;

    public CreateSensorDataUseCase(SensorDataRepository sensorDataRepository, SensorRepository sensorRepository, SensorDataMapper sensorDataMapper) {
        this.sensorDataRepository = sensorDataRepository;
        this.sensorRepository = sensorRepository;
        this.sensorDataMapper = sensorDataMapper;
    }

    public SensorDataResponseDTO handle(SensorDataRequestDTO dto, Long userId) {
        logger.info("--------- Ingesting new sensor data ---------");

        // Check if sensor exists and belongs to the user
        Sensor sensor = sensorRepository.findByIdAndCrop_Farm_User_Id(dto.sensorId(), userId)
                .orElseThrow(() -> new ResourceNotFoundException("Sensor with ID " + dto.sensorId() + " not found for this user."));

        SensorData newSensorData = sensorDataMapper.toEntity(dto);
        newSensorData.setSensor(sensor);

        SensorData savedData = sensorDataRepository.save(newSensorData);

        logger.info("--------- Sensor data ingested successfully with ID: {} ---------", savedData.getId());

        return sensorDataMapper.toResponseDTO(savedData);
    }
}
