package com.terrasync.terrasync_backend.controller;

import com.terrasync.terrasync_backend.dto.CropTypeRequestDTO;
import com.terrasync.terrasync_backend.dto.CropTypeResponseDTO;
import com.terrasync.terrasync_backend.service.cropType.CropTypeServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/crop-type")
public class CropTypeController {

    private final CropTypeServices cropTypeServices;

    @Autowired
    public CropTypeController(CropTypeServices cropTypeServices) {
        this.cropTypeServices = cropTypeServices;
    }

    @PostMapping
    ResponseEntity<CropTypeResponseDTO> create(@Valid CropTypeRequestDTO cropTypeRequestDTO){
        CropTypeResponseDTO createdCropType = cropTypeServices.createNewCropType(cropTypeRequestDTO);
        // TODO: Adicionar links hateos quanto antes!

        return ResponseEntity.status(HttpStatus.CREATED).body(createdCropType);
        // TODO: Terminar de tratar o Cross-Site Scripting (XSS) :)
    }

}
