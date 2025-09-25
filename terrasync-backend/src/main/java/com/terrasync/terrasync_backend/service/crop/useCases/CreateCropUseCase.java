package com.terrasync.terrasync_backend.service.crop.useCases;

import com.terrasync.terrasync_backend.dto.crop.CropRequestDTO;
import com.terrasync.terrasync_backend.dto.crop.CropResponseDTO;
import com.terrasync.terrasync_backend.entity.Crop;
import com.terrasync.terrasync_backend.entity.CropType;
import com.terrasync.terrasync_backend.entity.Farm;
import com.terrasync.terrasync_backend.exception.domain.DuplicateResourceException;
import com.terrasync.terrasync_backend.exception.domain.ResourceNotFoundException;
import com.terrasync.terrasync_backend.mapper.CropMapper;
import com.terrasync.terrasync_backend.repository.CropRepository;
import com.terrasync.terrasync_backend.repository.CropTypeRepository;
import com.terrasync.terrasync_backend.repository.FarmRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateCropUseCase {

    private final Logger logger = LoggerFactory.getLogger(CreateCropUseCase.class);
    private final CropRepository cropRepository;
    private final FarmRepository farmRepository;
    private final CropTypeRepository cropTypeRepository;
    private final CropMapper cropMapper;

    @Autowired
    public CreateCropUseCase(
            CropRepository cropRepository,
            FarmRepository farmRepository,
            CropTypeRepository cropTypeRepository,
            CropMapper cropMapper
    ) {
        this.cropRepository = cropRepository;
        this.farmRepository = farmRepository;
        this.cropTypeRepository = cropTypeRepository;
        this.cropMapper = cropMapper;
    }

    public CropResponseDTO handle(CropRequestDTO dto, Long userId) {
        logger.info("--------- Trying to create a new Crop! ---------");

        Farm farm = farmRepository.findByIdAndUser_Id(dto.farmId(), userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Farm with ID: " + dto.farmId() + " not found for this user."
                ));

        if (cropRepository.existsByNameIgnoreCaseAndFarm_Id(dto.name(), dto.farmId())) {
            throw new DuplicateResourceException(
                    "There's already a crop named '" + dto.name() + "' on this farm."
            );
        }

        CropType cropType = cropTypeRepository.findById(dto.cropTypeId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Crop Type with ID: " + dto.cropTypeId() + " not founded."
                ));

        Crop newCrop = cropMapper.toEntity(dto);
        newCrop.setFarm(farm);
        newCrop.setCropType(cropType);
        Crop savedCrop = cropRepository.save(newCrop);

        return cropMapper.toResponseDTO(savedCrop);
    }
}
