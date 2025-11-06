package com.terrasync.backend.service.sensor.useCases;

import com.terrasync.backend.dto.sensor.SensorRequestDTO;
import com.terrasync.backend.dto.sensor.SensorResponseDTO;
import com.terrasync.backend.entity.Sensor;
import com.terrasync.backend.exception.domain.DuplicateResourceException;
import com.terrasync.backend.exception.domain.ResourceNotFoundException;
import com.terrasync.backend.mapper.SensorMapper;
import com.terrasync.backend.repository.SensorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class UpdateSensorUseCase {

    private final Logger logger = LoggerFactory.getLogger(UpdateSensorUseCase.class);
    private final SensorRepository sensorRepository;
    private final SensorMapper sensorMapper;

    public UpdateSensorUseCase(SensorRepository sensorRepository, SensorMapper sensorMapper) {
        this.sensorRepository = sensorRepository;
        this.sensorMapper = sensorMapper;
    }

    public SensorResponseDTO handle(Long sensorId, Long userId, SensorRequestDTO requestDTO) {
        logger.info("--------- Updating sensor ID: {} for user: {} ---------", sensorId, userId);

        Sensor existingSensor = sensorRepository.findByIdAndCrop_Farm_User_Id(sensorId, userId)
                .orElseThrow(() -> {
                    logger.error("Sensor with ID {} not found for user {}", sensorId, userId);
                    return new ResourceNotFoundException("Sensor with ID " + sensorId + " not found for this user.");
                });

        // Check for duplicate device UUID if it is being changed
        if (requestDTO.deviceUuid() != null && !requestDTO.deviceUuid().equalsIgnoreCase(existingSensor.getDeviceUuid())) {
            if (sensorRepository.existsByDeviceUuid(requestDTO.deviceUuid())) {
                throw new DuplicateResourceException("Sensor with Device UUID '" + requestDTO.deviceUuid() + "' already exists.");
            }
        }

        // Update entity from DTO
        sensorMapper.updateFromDTO(requestDTO, existingSensor);

        Sensor updatedSensor = sensorRepository.save(existingSensor);

        logger.info("--------- Sensor updated successfully ---------");

        return sensorMapper.toResponseDTO(updatedSensor);
    }
}
