package com.terrasync.terrasyncBackend.service.cropType.useCases;

import com.terrasync.terrasyncBackend.dto.cropType.CropTypeRequestDTO;
import com.terrasync.terrasyncBackend.dto.cropType.CropTypeResponseDTO;
import com.terrasync.terrasyncBackend.entity.CropType;
import com.terrasync.terrasyncBackend.exception.domain.DuplicateResourceException;
import com.terrasync.terrasyncBackend.mapper.CropTypeMapper;
import com.terrasync.terrasyncBackend.repository.CropTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateCropTypeUseCase {

    private final Logger logger = LoggerFactory.getLogger(CreateCropTypeUseCase.class);
    private final CropTypeRepository cropTypeRepository;
    private final CropTypeMapper cropTypeMapper;

    @Autowired
    public CreateCropTypeUseCase(CropTypeRepository cropTypeRepository, CropTypeMapper cropTypeMapper) {
        this.cropTypeRepository = cropTypeRepository;
        this.cropTypeMapper = cropTypeMapper;
    }

    public CropTypeResponseDTO handle(CropTypeRequestDTO cropTypeRequestDTO){
        logger.info("--------- Trying to Create a new Crop Type! ---------");

        if(cropTypeRepository.existsByNameIgnoreCase(cropTypeRequestDTO.name())){
            throw new DuplicateResourceException("Crop Type with name '" + cropTypeRequestDTO.name() + "' already exists.");
            // TODO: Depois criar uma exceção customizada aqui. Por agora RuntimeException funciona para testar.
        }

        CropType cropType = cropTypeMapper.toEntity(cropTypeRequestDTO);
        cropType = cropTypeRepository.save(cropType);
        logger.info("--------- New Crop Type Created! ---------");
        return cropTypeMapper.toResponseDTO(cropType);
    }

}
