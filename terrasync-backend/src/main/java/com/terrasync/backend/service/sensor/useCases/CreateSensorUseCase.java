package com.terrasync.backend.service.sensor.useCases;

import com.terrasync.backend.dto.sensor.SensorRequestDTO;
import com.terrasync.backend.dto.sensor.SensorResponseDTO;
import com.terrasync.backend.entity.Crop;
import com.terrasync.backend.entity.Sensor;
import com.terrasync.backend.exception.domain.DuplicateResourceException;
import com.terrasync.backend.exception.domain.ResourceNotFoundException;
import com.terrasync.backend.mapper.SensorMapper;
import com.terrasync.backend.repository.CropRepository;
import com.terrasync.backend.repository.SensorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CreateSensorUseCase {

    private final Logger logger = LoggerFactory.getLogger(CreateSensorUseCase.class);
    private final SensorRepository sensorRepository;
    private final CropRepository cropRepository;
    private final SensorMapper sensorMapper;

    public CreateSensorUseCase(SensorRepository sensorRepository, CropRepository cropRepository, SensorMapper sensorMapper) {
        this.sensorRepository = sensorRepository;
        this.cropRepository = cropRepository;
        this.sensorMapper = sensorMapper;
    }

    public SensorResponseDTO handle(SensorRequestDTO dto, Long userId) {
        logger.info("--------- Creating a new sensor ---------");

        // Check if crop exists and belongs to the user
        Crop crop = cropRepository.findByIdAndFarm_User_Id(dto.cropId(), userId)
                .orElseThrow(() -> new ResourceNotFoundException("Crop with ID " + dto.cropId() + " not found for this user."));

        // Check if device UUID is unique
        if (sensorRepository.existsByDeviceUuid(dto.deviceUuid())) {
            throw new DuplicateResourceException("Sensor with Device UUID '" + dto.deviceUuid() + "' already exists.");
        }

        Sensor newSensor = sensorMapper.toEntity(dto);
        newSensor.setCrop(crop);

        if (newSensor.getStatus() == null) {
            newSensor.setStatus(true);
        }

        Sensor savedSensor = sensorRepository.save(newSensor);

        logger.info("--------- Sensor created successfully with ID: {} ---------", savedSensor.getId());

        return sensorMapper.toResponseDTO(savedSensor);
    }
}
