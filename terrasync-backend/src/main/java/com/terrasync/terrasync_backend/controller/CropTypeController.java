package com.terrasync.terrasync_backend.controller;

import com.terrasync.terrasync_backend.dto.cropType.CropTypeRequestDTO;
import com.terrasync.terrasync_backend.dto.cropType.CropTypeResponseDTO;
import com.terrasync.terrasync_backend.service.cropType.CropTypeServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// TODO: Adicionar links hateos quanto antes!
@RestController
@RequestMapping("/api/v1/crop-type")
public class CropTypeController {

    private final CropTypeServices cropTypeServices;

    @Autowired
    public CropTypeController(CropTypeServices cropTypeServices) {
        this.cropTypeServices = cropTypeServices;
    }

    @PostMapping
    public ResponseEntity<CropTypeResponseDTO> create(@Valid @RequestBody CropTypeRequestDTO cropTypeRequestDTO) {
        CropTypeResponseDTO createdCropType = cropTypeServices.createNewCropType(cropTypeRequestDTO);

        // TODO: Terminar de corrigir a vulnerabilidade de Cross-Site Scripting (XSS)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCropType);
    }

    // TODO: Adicionar paginação
    @GetMapping // Listar todos os CropTypes
    public ResponseEntity<List<CropTypeResponseDTO>> findAll() {

        List<CropTypeResponseDTO> cropTypes = cropTypeServices.findAllCropTypes();
        return ResponseEntity.ok(cropTypes);
    }

    @GetMapping("/{id}") // Buscar um CropType pelo ID
    public ResponseEntity<CropTypeResponseDTO> findById(@PathVariable Long id) {
        CropTypeResponseDTO cropType = cropTypeServices.findCropTypeById(id);
        return ResponseEntity.ok(cropType);
    }

    @PutMapping("/{id}") // Atualizar um CropType pelo ID
    public ResponseEntity<CropTypeResponseDTO> update(@PathVariable Long id,
                                                      @Valid @RequestBody CropTypeRequestDTO cropTypeRequestDTO) {
        CropTypeResponseDTO updatedCropType = cropTypeServices.updateCropType(id, cropTypeRequestDTO);
        return ResponseEntity.ok(updatedCropType);
    }

    @DeleteMapping("/{id}") // Deletar um CropType pelo ID
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cropTypeServices.deleteCropType(id);
        return ResponseEntity.noContent().build();
    }

}
