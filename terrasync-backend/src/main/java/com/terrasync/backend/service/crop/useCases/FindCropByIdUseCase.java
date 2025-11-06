package com.terrasync.backend.service.crop.useCases;

import com.terrasync.backend.dto.crop.CropResponseDTO;
import com.terrasync.backend.entity.Crop;
import com.terrasync.backend.exception.domain.ResourceNotFoundException;
import com.terrasync.backend.mapper.CropMapper;
import com.terrasync.backend.repository.CropRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class FindCropByIdUseCase {

    private final Logger logger = LoggerFactory.getLogger(FindCropByIdUseCase.class);
    private final CropRepository cropRepository;
    private final CropMapper cropMapper;

    public FindCropByIdUseCase(CropRepository cropRepository, CropMapper cropMapper) {
        this.cropRepository = cropRepository;
        this.cropMapper = cropMapper;
    }

    public CropResponseDTO handle(Long cropId, Long userId) {
        logger.info("--------- Finding crop by ID: {} for user: {} ---------", cropId, userId);

        Crop crop = cropRepository
                .findByIdAndFarm_User_Id(cropId, userId)
                .orElseThrow(() -> {
                    logger.error("Crop with ID {} not found for this user {}", cropId, userId);
                    return new ResourceNotFoundException("Crop with ID " + cropId + " not found for this user.");
                });

        logger.info("--------- Crop found successfully ---------");
        return cropMapper.toResponseDTO(crop);
    }
}
