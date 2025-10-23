package com.terrasync.backend.service.cropType.useCases;

import com.terrasync.backend.dto.cropType.CropTypeResponseDTO;
import com.terrasync.backend.mapper.CropTypeMapper;
import com.terrasync.backend.repository.CropTypeRepository;
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
        return cropTypeMapper.toResponseDTOList(cropTypeRepository.findAll());
    }
}
