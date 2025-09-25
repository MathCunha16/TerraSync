package com.terrasync.terrasync_backend.controller;

import com.terrasync.terrasync_backend.dto.farm.FarmRequestDTO;
import com.terrasync.terrasync_backend.dto.farm.FarmResponseDTO;
import com.terrasync.terrasync_backend.service.farm.FarmServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
