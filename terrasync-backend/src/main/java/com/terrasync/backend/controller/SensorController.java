package com.terrasync.backend.controller;

import com.terrasync.backend.dto.sensor.SensorRequestDTO;
import com.terrasync.backend.dto.sensor.SensorResponseDTO;
import com.terrasync.backend.service.sensor.SensorServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sensors")
public class SensorController {

    private final SensorServices sensorServices;

    @Autowired
    public SensorController(SensorServices sensorServices) {
        this.sensorServices = sensorServices;
    }

    @PostMapping
    public ResponseEntity<SensorResponseDTO> create(@Valid @RequestBody SensorRequestDTO sensorRequestDTO) {
        // TODO: Substituir este ID "hardcoded" pelo ID do utilizador autenticado quando a segurança estiver implementada.
        Long currentUserId = 1L;
        SensorResponseDTO createdSensor = sensorServices.createSensor(sensorRequestDTO, currentUserId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSensor);
    }

    @GetMapping("/{sensorId}")
    public ResponseEntity<SensorResponseDTO> findById(@PathVariable Long sensorId) {
        // TODO: Substituir ID "hardcoded" pelo ID do utilizador autenticado
        Long currentUserId = 1L;
        SensorResponseDTO sensor = sensorServices.getSensorById(sensorId, currentUserId);
        return ResponseEntity.ok(sensor);
    }

    @GetMapping("/crop/{cropId}")
    public ResponseEntity<List<SensorResponseDTO>> findAllByCropId(@PathVariable Long cropId) {
        // TODO: Substituir ID "hardcoded" pelo ID do utilizador autenticado
        Long currentUserId = 1L;
        List<SensorResponseDTO> sensors = sensorServices.getAllSensorsByCrop(cropId, currentUserId);
        return ResponseEntity.ok(sensors);
    }

    @PutMapping("/{sensorId}")
    public ResponseEntity<SensorResponseDTO> update(
            @PathVariable Long sensorId,
            @Valid @RequestBody SensorRequestDTO sensorRequestDTO) {
        // TODO: O campo cropId no SensorRequestDTO não deve ser atualizável. Considerar ignorá-lo no mapper ou validá-lo aqui.
        // TODO: Substituir este ID "hardcoded" pelo ID do utilizador autenticado
        Long currentUserId = 1L;
        SensorResponseDTO updatedSensor = sensorServices.updateSensor(sensorId, currentUserId, sensorRequestDTO);
        return ResponseEntity.ok(updatedSensor);
    }

    @DeleteMapping("/{sensorId}")
    public ResponseEntity<String> delete(@PathVariable Long sensorId) {
        // TODO: Substituir este ID "hardcoded" pelo ID do utilizador autenticado
        Long currentUserId = 1L;
        String message = sensorServices.deleteSensor(sensorId, currentUserId);
        return ResponseEntity.ok(message);
    }
}
