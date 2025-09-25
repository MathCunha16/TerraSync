package com.terrasync.terrasync_backend.service.farm.useCases;

import com.terrasync.terrasync_backend.entity.Farm;
import com.terrasync.terrasync_backend.exception.domain.ResourceNotFoundException;
import com.terrasync.terrasync_backend.mapper.FarmMapper;
import com.terrasync.terrasync_backend.repository.FarmRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteFarmUseCase {

    private Logger logger = org.slf4j.LoggerFactory.getLogger(DeleteFarmUseCase.class);
    private final FarmRepository farmRepository;
    private final FarmMapper farmMapper;

    @Autowired
    public DeleteFarmUseCase(FarmRepository farmRepository, FarmMapper farmMapper) {
        this.farmRepository = farmRepository;
        this.farmMapper = farmMapper;
    }

    public void handle(Long farmId, Long userId){
        logger.info("------- Deactivating farm with ID -------");

        Farm farmToDeactivate = farmRepository.findByIdAndUser_Id(farmId, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Farm with ID " + farmId + " not found for this user."));

        // Desativando a farm ao invés de deletar
        farmToDeactivate.setActive(false);
        farmRepository.save(farmToDeactivate);
    }
}
