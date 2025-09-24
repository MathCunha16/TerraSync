package com.terrasync.terrasync_backend.service.cropType.useCases;

import com.terrasync.terrasync_backend.dto.CropTypeRequestDTO;
import com.terrasync.terrasync_backend.dto.CropTypeResponseDTO;
import com.terrasync.terrasync_backend.entity.CropType;
import com.terrasync.terrasync_backend.mapper.CropTypeMapper;
import com.terrasync.terrasync_backend.repository.CropTypeRepository;
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
            throw new RuntimeException("Crop Type with name '" + cropTypeRequestDTO.name() + "' already exists.");
            // TODO: Depois criar uma exceção customizada aqui. Por agora RuntimeException funciona para testar.
        }

        CropType cropType = cropTypeMapper.toEntity(cropTypeRequestDTO);
        cropType = cropTypeRepository.save(cropType);
        logger.info("--------- New Crop Type Created! ---------");
        return cropTypeMapper.toResponseDTO(cropType);
    }

}
