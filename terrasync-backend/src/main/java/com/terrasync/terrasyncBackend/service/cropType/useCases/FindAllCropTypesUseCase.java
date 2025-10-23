package com.terrasync.terrasyncBackend.service.cropType.useCases;

import com.terrasync.terrasyncBackend.dto.cropType.CropTypeResponseDTO;
import com.terrasync.terrasyncBackend.mapper.CropTypeMapper;
import com.terrasync.terrasyncBackend.repository.CropTypeRepository;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FindAllCropTypesUseCase {

    private final Logger logger = org.slf4j.LoggerFactory.getLogger(FindAllCropTypesUseCase.class);
    private final CropTypeRepository cropTypeRepository;
    private final CropTypeMapper cropTypeMapper;

    public FindAllCropTypesUseCase(CropTypeRepository cropTypeRepository, CropTypeMapper cropTypeMapper) {
        this.cropTypeRepository = cropTypeRepository;
        this.cropTypeMapper = cropTypeMapper;
    }

    public List<CropTypeResponseDTO> handle (){
        logger.info("---------Finding All Crop Types! ---------");
        List<CropTypeResponseDTO> cropTypes = cropTypeMapper.toResponseDTOList(cropTypeRepository.findAll());
        return cropTypes;
    }
}
