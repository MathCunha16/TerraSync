package com.terrasync.backend.service.sensor.useCases;

import com.terrasync.backend.dto.sensor.SensorResponseDTO;
import com.terrasync.backend.entity.Sensor;
import com.terrasync.backend.exception.domain.ResourceNotFoundException;
import com.terrasync.backend.mapper.SensorMapper;
import com.terrasync.backend.repository.SensorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class FindSensorByIdUseCase {

    private final Logger logger = LoggerFactory.getLogger(FindSensorByIdUseCase.class);
    private final SensorRepository sensorRepository;
    private final SensorMapper sensorMapper;

    public FindSensorByIdUseCase(SensorRepository sensorRepository, SensorMapper sensorMapper) {
        this.sensorRepository = sensorRepository;
        this.sensorMapper = sensorMapper;
    }

    public SensorResponseDTO handle(Long sensorId, Long userId) {
        logger.info("--------- Finding sensor by ID: {} for user: {} ---------", sensorId, userId);

        Sensor sensor = sensorRepository.findByIdAndCrop_Farm_User_Id(sensorId, userId)
                .orElseThrow(() -> {
                    logger.error("Sensor with ID {} not found for user {}", sensorId, userId);
                    return new ResourceNotFoundException("Sensor with ID " + sensorId + " not found for this user.");
                });

        logger.info("--------- Sensor found successfully ---------");
        return sensorMapper.toResponseDTO(sensor);
    }
}
