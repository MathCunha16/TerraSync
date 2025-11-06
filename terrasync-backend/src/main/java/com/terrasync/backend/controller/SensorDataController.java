package com.terrasync.backend.controller;

import com.terrasync.backend.dto.sensordata.SensorDataRequestDTO;
import com.terrasync.backend.dto.sensordata.SensorDataResponseDTO;
import com.terrasync.backend.service.sensordata.SensorDataServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sensor-data")
public class SensorDataController {

    private final SensorDataServices sensorDataServices;

    @Autowired
    public SensorDataController(SensorDataServices sensorDataServices) {
        this.sensorDataServices = sensorDataServices;
    }

    @PostMapping
    public ResponseEntity<SensorDataResponseDTO> create(@Valid @RequestBody SensorDataRequestDTO sensorDataRequestDTO) {
        // TODO: Substituir este ID "hardcoded" pelo ID do utilizador autenticado quando a segurança estiver implementada.
        Long currentUserId = 1L;
        SensorDataResponseDTO createdSensorData = sensorDataServices.createSensorData(sensorDataRequestDTO, currentUserId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSensorData);
    }

    @GetMapping("/sensor/{sensorId}")
    public ResponseEntity<List<SensorDataResponseDTO>> findAllBySensorId(@PathVariable Long sensorId) {
        // TODO: Substituir ID "hardcoded" pelo ID do utilizador autenticado
        Long currentUserId = 1L;
        List<SensorDataResponseDTO> sensorData = sensorDataServices.getAllSensorDataBySensor(sensorId, currentUserId);
        return ResponseEntity.ok(sensorData);
    }
}
