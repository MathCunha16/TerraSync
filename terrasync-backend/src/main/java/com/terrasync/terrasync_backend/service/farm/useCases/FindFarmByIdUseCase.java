package com.terrasync.terrasync_backend.service.farm.useCases;

import com.terrasync.terrasync_backend.dto.farm.FarmResponseDTO;
import com.terrasync.terrasync_backend.entity.CropType;
import com.terrasync.terrasync_backend.entity.Farm;
import com.terrasync.terrasync_backend.exception.domain.ResourceNotFoundException;
import com.terrasync.terrasync_backend.mapper.FarmMapper;
import com.terrasync.terrasync_backend.repository.FarmRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FindFarmByIdUseCase {

    private Logger logger = org.slf4j.LoggerFactory.getLogger(FindFarmByIdUseCase.class);
    private final FarmRepository farmRepository;
    private final FarmMapper farmMapper;

    @Autowired
    public FindFarmByIdUseCase(FarmRepository farmRepository, FarmMapper farmMapper) {
        this.farmRepository = farmRepository;
        this.farmMapper = farmMapper;
    }

    public FarmResponseDTO handle(Long farmId, Long userId) {
        logger.info("--------- Trying to Find Farm by ID ---------");

        Farm farm = farmRepository
                .findByIdAndUser_Id(farmId, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Farm with ID " + farmId + " not found for this user."));

        return farmMapper.toResponseDTO(farm);
    }

}
