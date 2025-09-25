package com.terrasync.terrasync_backend.service.cropType.useCases;

import com.terrasync.terrasync_backend.dto.cropType.CropTypeRequestDTO;
import com.terrasync.terrasync_backend.dto.cropType.CropTypeResponseDTO;
import com.terrasync.terrasync_backend.entity.CropType;
import com.terrasync.terrasync_backend.exception.domain.DuplicateResourceException;
import com.terrasync.terrasync_backend.exception.domain.ResourceNotFoundException;
import com.terrasync.terrasync_backend.mapper.CropTypeMapper;
import com.terrasync.terrasync_backend.repository.CropTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UpdateCropTypeUseCase {

    private final Logger logger = LoggerFactory.getLogger(UpdateCropTypeUseCase.class);
    private final CropTypeRepository cropTypeRepository;
    private final CropTypeMapper cropTypeMapper;

    @Autowired
    public UpdateCropTypeUseCase (CropTypeRepository cropTypeRepository, CropTypeMapper cropTypeMapper){
        this.cropTypeRepository = cropTypeRepository;
        this.cropTypeMapper = cropTypeMapper;
    }

    public CropTypeResponseDTO handle(Long id, CropTypeRequestDTO dto) {
        logger.info("--------- Trying to Update Crop Type with ID ---------");

        // Busca a entidade ou lança 404. Mais eficiente que o existsById do JPA.
        CropType cropTypeToUpdate = cropTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Crop Type with ID " + id + " not found."));

        // Valida se o novo nome já está em uso
        validateNameUniqueness(dto.name(), id);
        cropTypeMapper.updateFromDTO(dto, cropTypeToUpdate);
        CropType updatedCropType = cropTypeRepository.save(cropTypeToUpdate);
        return cropTypeMapper.toResponseDTO(updatedCropType);
    }

    private void validateNameUniqueness(String name, Long currentId) {
        Optional<CropType> existingCropType = cropTypeRepository.findByNameIgnoreCase(name);
        if (existingCropType.isPresent() && !existingCropType.get().getId().equals(currentId)) {
            throw new DuplicateResourceException("Crop Type with name '" + name + "' already exists.");
        }
    }

}
