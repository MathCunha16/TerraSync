package com.terrasync.terrasync_backend.controller;

import com.terrasync.terrasync_backend.dto.farm.FarmRequestDTO;
import com.terrasync.terrasync_backend.dto.farm.FarmResponseDTO;
import com.terrasync.terrasync_backend.service.farm.FarmServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/farm")
public class FarmController {

    private final FarmServices farmServices;

    @Autowired
    public FarmController(FarmServices farmServices) {
        this.farmServices = farmServices;
    }

    // TODO: Tratar Cross-Site Scripting (XSS)
    @PostMapping
    public ResponseEntity<FarmResponseDTO> create(@Valid @RequestBody FarmRequestDTO farmRequestDTO) {

        // TODO: Substituir este ID "hardcoded" pelo ID do utilizador autenticado quando a segurança estiver implementada.
        Long currentUserId = 1L;

        FarmResponseDTO createdFarm = farmServices.createNewFarm(farmRequestDTO, currentUserId);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdFarm);
    }

    // TODO: Adicionar paginação
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<FarmResponseDTO>> findAllByUserId(@PathVariable Long userId) {
        List<FarmResponseDTO> farms = farmServices.findAllFarmsByUserId(userId);
        return ResponseEntity.ok(farms);
    }

    @GetMapping("/{farmId}")
    public ResponseEntity<FarmResponseDTO> findById(@PathVariable Long farmId) {
        // TODO: Substituir ID "hardcoded" pelo ID do utilizador autenticado
        Long currentUserId = 1L;
        FarmResponseDTO farm = farmServices.findFarmById(farmId, currentUserId);
        return ResponseEntity.ok(farm);
    }

    @PutMapping("/{farmId}")
    public ResponseEntity<FarmResponseDTO> update(
            @PathVariable Long farmId,
            @Valid @RequestBody FarmRequestDTO farmRequestDTO) {

        // TODO: Substituir este ID "hardcoded" pelo ID do utilizador autenticado
        Long currentUserId = 1L;
        FarmResponseDTO updatedFarm = farmServices.updateFarm(farmId, currentUserId, farmRequestDTO);
        return ResponseEntity.ok(updatedFarm);
    }

    @DeleteMapping("{farmId}")
    public ResponseEntity<Void> delete(@PathVariable Long farmId){
        // TODO: Substituir este ID "hardcoded" pelo ID do utilizador autenticado
        Long currentUserId = 1L;

        farmServices.deleteFarm(farmId, currentUserId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
