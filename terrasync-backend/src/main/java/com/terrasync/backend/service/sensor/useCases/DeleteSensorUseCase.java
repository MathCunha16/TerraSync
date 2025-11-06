package com.terrasync.backend.service.sensor.useCases;

import com.terrasync.backend.entity.Sensor;
import com.terrasync.backend.exception.domain.ResourceNotFoundException;
import com.terrasync.backend.repository.SensorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DeleteSensorUseCase {

    private final Logger logger = LoggerFactory.getLogger(DeleteSensorUseCase.class);
    private final SensorRepository sensorRepository;

    public DeleteSensorUseCase(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public String handle(Long sensorId, Long userId) {
        logger.info("--------- Deleting sensor ID: {} for user: {} ---------", sensorId, userId);

        Sensor sensor = sensorRepository.findByIdAndCrop_Farm_User_Id(sensorId, userId)
                .orElseThrow(() -> {
                    logger.error("Sensor with ID {} not found for user {}", sensorId, userId);
                    return new ResourceNotFoundException("Sensor with ID " + sensorId + " not found for this user.");
                });

        sensorRepository.delete(sensor);

        logger.info("--------- Sensor deleted successfully ---------");

        return "Sensor with ID " + sensorId + " deleted successfully.";
    }
}
