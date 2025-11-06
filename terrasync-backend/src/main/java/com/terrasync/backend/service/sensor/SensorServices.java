package com.terrasync.backend.service.sensor;

import com.terrasync.backend.dto.sensor.SensorRequestDTO;
import com.terrasync.backend.dto.sensor.SensorResponseDTO;
import com.terrasync.backend.service.sensor.useCases.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorServices {

    private final CreateSensorUseCase createSensorUseCase;
    private final FindSensorByIdUseCase findSensorByIdUseCase;
    private final FindAllSensorsByCropUseCase findAllSensorsByCropUseCase;
    private final UpdateSensorUseCase updateSensorUseCase;
    private final DeleteSensorUseCase deleteSensorUseCase;

    public SensorServices(CreateSensorUseCase createSensorUseCase,
                          FindSensorByIdUseCase findSensorByIdUseCase,
                          FindAllSensorsByCropUseCase findAllSensorsByCropUseCase,
                          UpdateSensorUseCase updateSensorUseCase,
                          DeleteSensorUseCase deleteSensorUseCase) {
        this.createSensorUseCase = createSensorUseCase;
        this.findSensorByIdUseCase = findSensorByIdUseCase;
        this.findAllSensorsByCropUseCase = findAllSensorsByCropUseCase;
        this.updateSensorUseCase = updateSensorUseCase;
        this.deleteSensorUseCase = deleteSensorUseCase;
    }

    public SensorResponseDTO createSensor(SensorRequestDTO dto, Long userId) {
        return createSensorUseCase.handle(dto, userId);
    }

    public SensorResponseDTO getSensorById(Long sensorId, Long userId) {
        return findSensorByIdUseCase.handle(sensorId, userId);
    }

    public List<SensorResponseDTO> getAllSensorsByCrop(Long cropId, Long userId) {
        return findAllSensorsByCropUseCase.handle(cropId, userId);
    }

    public SensorResponseDTO updateSensor(Long sensorId, Long userId, SensorRequestDTO requestDTO) {
        return updateSensorUseCase.handle(sensorId, userId, requestDTO);
    }

    public String deleteSensor(Long sensorId, Long userId) {
        return deleteSensorUseCase.handle(sensorId, userId);
    }
}
