package com.terrasync.backend.service.crop.useCases;

import com.terrasync.backend.dto.crop.CropRequestDTO;
import com.terrasync.backend.dto.crop.CropResponseDTO;
import com.terrasync.backend.entity.Crop;
import com.terrasync.backend.entity.CropType;
import com.terrasync.backend.exception.domain.DuplicateResourceException;
import com.terrasync.backend.exception.domain.ResourceNotFoundException;
import com.terrasync.backend.mapper.CropMapper;
import com.terrasync.backend.repository.CropRepository;
import com.terrasync.backend.repository.CropTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class UpdateCropUseCase {

    private final Logger logger = LoggerFactory.getLogger(UpdateCropUseCase.class);
    private final CropRepository cropRepository;
    private final CropTypeRepository cropTypeRepository;
    private final CropMapper cropMapper;

    public UpdateCropUseCase(CropRepository cropRepository, CropTypeRepository cropTypeRepository, CropMapper cropMapper) {
        this.cropRepository = cropRepository;
        this.cropTypeRepository = cropTypeRepository;
        this.cropMapper = cropMapper;
    }

    public CropResponseDTO handle(Long cropId, Long userId, CropRequestDTO requestDTO) {
        logger.info("--------- Updating crop ID: {} for user: {} ---------", cropId, userId);

        Crop existingCrop = cropRepository.findByIdAndFarm_User_Id(cropId, userId)
                .orElseThrow(() -> {
                    logger.error("Crop with ID {} not found for user {}", cropId, userId);
                    return new ResourceNotFoundException("Crop with ID " + cropId + " not found for this user.");
                });

        // Check for duplicate name if the name is being changed
        if (requestDTO.name() != null && !requestDTO.name().equalsIgnoreCase(existingCrop.getName())) {
            if (cropRepository.existsByNameIgnoreCaseAndFarm_Id(requestDTO.name(), existingCrop.getFarm().getId())) {
                throw new DuplicateResourceException("A crop with the name '" + requestDTO.name() + "' already exists in this farm.");
            }
        }

        // Check if crop type exists if it is being changed
        if (requestDTO.cropTypeId() != null && !requestDTO.cropTypeId().equals(existingCrop.getCropType().getId())) {
            CropType cropType = cropTypeRepository.findById(requestDTO.cropTypeId())
                    .orElseThrow(() -> new ResourceNotFoundException("CropType with ID " + requestDTO.cropTypeId() + " not found."));
            existingCrop.setCropType(cropType);
        }

        // Update entity from DTO
        cropMapper.updateFromDTO(requestDTO, existingCrop);

        Crop updatedCrop = cropRepository.save(existingCrop);

        logger.info("--------- Crop updated successfully ---------");

        return cropMapper.toResponseDTO(updatedCrop);
    }
}
