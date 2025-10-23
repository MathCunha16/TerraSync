package com.terrasync.terrasyncBackend.service.cropType.useCases;

import com.terrasync.terrasyncBackend.dto.cropType.CropTypeResponseDTO;
import com.terrasync.terrasyncBackend.entity.CropType;
import com.terrasync.terrasyncBackend.exception.domain.ResourceNotFoundException;
import com.terrasync.terrasyncBackend.mapper.CropTypeMapper;
import com.terrasync.terrasyncBackend.repository.CropTypeRepository;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class FindCropTypeByIdUseCase {

    private final Logger logger = org.slf4j.LoggerFactory.getLogger(FindCropTypeByIdUseCase.class);
    private final CropTypeRepository cropTypeRepository;
    private final CropTypeMapper cropTypeMapper;

    public FindCropTypeByIdUseCase(CropTypeRepository cropTypeRepository, CropTypeMapper cropTypeMapper) {
        this.cropTypeRepository = cropTypeRepository;
        this.cropTypeMapper = cropTypeMapper;
    }

    public CropTypeResponseDTO handle(Long id){
        logger.info("--------- Trying to Find a Crop Type by ID! ---------");
        CropType cropType = cropTypeRepository
                .findById(id).orElseThrow(() -> new ResourceNotFoundException("Crop Type with ID " + id + " not found."));
        return cropTypeMapper.toResponseDTO(cropType);
    }
}
