package com.terrasync.backend.service.sensor.useCases;

import com.terrasync.backend.dto.sensor.SensorResponseDTO;
import com.terrasync.backend.entity.Sensor;
import com.terrasync.backend.exception.domain.ResourceNotFoundException;
import com.terrasync.backend.mapper.SensorMapper;
import com.terrasync.backend.repository.CropRepository;
import com.terrasync.backend.repository.SensorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FindAllSensorsByCropUseCase {

    private final Logger logger = LoggerFactory.getLogger(FindAllSensorsByCropUseCase.class);
    private final SensorRepository sensorRepository;
    private final CropRepository cropRepository;
    private final SensorMapper sensorMapper;

    public FindAllSensorsByCropUseCase(SensorRepository sensorRepository, CropRepository cropRepository, SensorMapper sensorMapper) {
        this.sensorRepository = sensorRepository;
        this.cropRepository = cropRepository;
        this.sensorMapper = sensorMapper;
    }

    public List<SensorResponseDTO> handle(Long cropId, Long userId) {
        logger.info("--------- Listing all sensors for crop ID: {} for user: {} ---------", cropId, userId);

        // First, check if the crop exists and belongs to the user
        cropRepository.findByIdAndFarm_User_Id(cropId, userId).orElseThrow(() -> {
            logger.error("Crop with ID {} not found for user {}", cropId, userId);
            return new ResourceNotFoundException("Crop with ID " + cropId + " not found for this user.");
        });

        List<Sensor> sensors = sensorRepository.findAllByCrop_Id(cropId);

        logger.info("--------- Found {} sensors for crop ID: {} ---------", sensors.size(), cropId);

        return sensorMapper.toResponseDTOList(sensors);
    }
}
