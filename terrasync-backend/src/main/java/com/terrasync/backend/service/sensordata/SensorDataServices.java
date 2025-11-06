package com.terrasync.backend.service.sensordata;

import com.terrasync.backend.dto.sensordata.SensorDataRequestDTO;
import com.terrasync.backend.dto.sensordata.SensorDataResponseDTO;
import com.terrasync.backend.service.sensordata.useCases.CreateSensorDataUseCase;
import com.terrasync.backend.service.sensordata.useCases.FindAllSensorDataBySensorUseCase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorDataServices {

    private final CreateSensorDataUseCase createSensorDataUseCase;
    private final FindAllSensorDataBySensorUseCase findAllSensorDataBySensorUseCase;

    public SensorDataServices(CreateSensorDataUseCase createSensorDataUseCase, FindAllSensorDataBySensorUseCase findAllSensorDataBySensorUseCase) {
        this.createSensorDataUseCase = createSensorDataUseCase;
        this.findAllSensorDataBySensorUseCase = findAllSensorDataBySensorUseCase;
    }

    public SensorDataResponseDTO createSensorData(SensorDataRequestDTO dto, Long userId) {
        return createSensorDataUseCase.handle(dto, userId);
    }

    public List<SensorDataResponseDTO> getAllSensorDataBySensor(Long sensorId, Long userId) {
        return findAllSensorDataBySensorUseCase.handle(sensorId, userId);
    }
}
