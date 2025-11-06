package com.terrasync.backend.controller;

import com.terrasync.backend.dto.crop.CropRequestDTO;
import com.terrasync.backend.dto.crop.CropResponseDTO;
import com.terrasync.backend.service.crop.CropServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/crops")
public class CropController {

    private final CropServices cropServices;

    @Autowired
    public CropController(CropServices cropServices) {
        this.cropServices = cropServices;
    }

    @PostMapping
    public ResponseEntity<CropResponseDTO> create(@Valid @RequestBody CropRequestDTO cropRequestDTO) {
        // TODO: Substituir este ID "hardcoded" pelo ID do utilizador autenticado quando a segurança estiver implementada.
        Long currentUserId = 1L;
        CropResponseDTO createdCrop = cropServices.createCrop(cropRequestDTO, currentUserId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCrop);
    }

    @GetMapping("/{cropId}")
    public ResponseEntity<CropResponseDTO> findById(@PathVariable Long cropId) {
        // TODO: Substituir ID "hardcoded" pelo ID do utilizador autenticado
        Long currentUserId = 1L;
        CropResponseDTO crop = cropServices.getCropById(cropId, currentUserId);
        return ResponseEntity.ok(crop);
    }

    @GetMapping("/farm/{farmId}")
    public ResponseEntity<List<CropResponseDTO>> findAllByFarmId(@PathVariable Long farmId) {
        // TODO: Substituir ID "hardcoded" pelo ID do utilizador autenticado
        Long currentUserId = 1L;
        List<CropResponseDTO> crops = cropServices.getAllCropsByFarm(farmId, currentUserId);
        return ResponseEntity.ok(crops);
    }

    @PutMapping("/{cropId}")
    public ResponseEntity<CropResponseDTO> update(
            @PathVariable Long cropId,
            @Valid @RequestBody CropRequestDTO cropRequestDTO) {
        // TODO: Substituir este ID "hardcoded" pelo ID do utilizador autenticado
        Long currentUserId = 1L;
        CropResponseDTO updatedCrop = cropServices.updateCrop(cropId, currentUserId, cropRequestDTO);
        return ResponseEntity.ok(updatedCrop);
    }

    @DeleteMapping("/{cropId}")
    public ResponseEntity<String> delete(@PathVariable Long cropId) {
        // TODO: Substituir este ID "hardcoded" pelo ID do utilizador autenticado
        Long currentUserId = 1L;
        String message = cropServices.deleteCrop(cropId, currentUserId);
        return ResponseEntity.ok(message);
    }
}
