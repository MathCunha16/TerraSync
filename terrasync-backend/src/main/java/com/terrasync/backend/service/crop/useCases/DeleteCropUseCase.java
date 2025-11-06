package com.terrasync.backend.service.crop.useCases;

import com.terrasync.backend.entity.Crop;
import com.terrasync.backend.exception.domain.ResourceNotFoundException;
import com.terrasync.backend.repository.CropRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DeleteCropUseCase {

    private final Logger logger = LoggerFactory.getLogger(DeleteCropUseCase.class);
    private final CropRepository cropRepository;

    public DeleteCropUseCase(CropRepository cropRepository) {
        this.cropRepository = cropRepository;
    }

    public String handle(Long cropId, Long userId) {
        logger.info("--------- Deleting crop ID: {} for user: {} ---------", cropId, userId);

        Crop crop = cropRepository.findByIdAndFarm_User_Id(cropId, userId)
                .orElseThrow(() -> {
                    logger.error("Crop with ID {} not found for user {}", cropId, userId);
                    return new ResourceNotFoundException("Crop with ID " + cropId + " not found for this user.");
                });

        cropRepository.delete(crop);

        logger.info("--------- Crop deleted successfully ---------");

        return "Crop with ID " + cropId + " deleted successfully.";
    }
}
