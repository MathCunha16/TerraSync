package com.terrasync.backend.service.crop.useCases;

import com.terrasync.backend.dto.crop.CropResponseDTO;
import com.terrasync.backend.entity.Crop;
import com.terrasync.backend.exception.domain.ResourceNotFoundException;
import com.terrasync.backend.mapper.CropMapper;
import com.terrasync.backend.repository.CropRepository;
import com.terrasync.backend.repository.FarmRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FindAllCropsByFarmUseCase {

    private final Logger logger = LoggerFactory.getLogger(FindAllCropsByFarmUseCase.class);
    private final CropRepository cropRepository;
    private final FarmRepository farmRepository;
    private final CropMapper cropMapper;

    public FindAllCropsByFarmUseCase(CropRepository cropRepository, FarmRepository farmRepository, CropMapper cropMapper) {
        this.cropRepository = cropRepository;
        this.farmRepository = farmRepository;
        this.cropMapper = cropMapper;
    }

    public List<CropResponseDTO> handle(Long farmId, Long userId) {
        logger.info("--------- Listing all crops for farm ID: {} for user: {} ---------", farmId, userId);

        // First, check if the farm exists and belongs to the user
        farmRepository.findByIdAndUser_Id(farmId, userId).orElseThrow(() -> {
            logger.error("Farm with ID {} not found for user {}", farmId, userId);
            return new ResourceNotFoundException("Farm with ID " + farmId + " not found for this user.");
        });

        List<Crop> crops = cropRepository.findAllByFarm_Id(farmId);

        logger.info("--------- Found {} crops for farm ID: {} ---------", crops.size(), farmId);

        return cropMapper.toResponseDTOList(crops);
    }
}
