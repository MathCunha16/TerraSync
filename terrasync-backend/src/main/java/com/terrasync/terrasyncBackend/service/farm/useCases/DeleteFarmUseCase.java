package com.terrasync.terrasyncBackend.service.farm.useCases;

import com.terrasync.terrasyncBackend.entity.Farm;
import com.terrasync.terrasyncBackend.exception.domain.ResourceNotFoundException;
import com.terrasync.terrasyncBackend.repository.FarmRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteFarmUseCase {

    private Logger logger = org.slf4j.LoggerFactory.getLogger(DeleteFarmUseCase.class);
    private final FarmRepository farmRepository;

    @Autowired
    public DeleteFarmUseCase(FarmRepository farmRepository) {
        this.farmRepository = farmRepository;
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
