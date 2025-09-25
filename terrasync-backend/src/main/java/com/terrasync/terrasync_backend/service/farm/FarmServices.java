package com.terrasync.terrasync_backend.service.farm;

import com.terrasync.terrasync_backend.dto.farm.FarmRequestDTO;
import com.terrasync.terrasync_backend.dto.farm.FarmResponseDTO;
import com.terrasync.terrasync_backend.service.farm.useCases.CreateFarmUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FarmServices {

    private final CreateFarmUseCase createFarmUseCase;

    @Autowired
    public FarmServices (CreateFarmUseCase createFarmUseCase){
        this.createFarmUseCase = createFarmUseCase;
    }

    // Metodo para cirar nova fazenda
    public FarmResponseDTO createNewFarm(FarmRequestDTO farmRequestDTO, Long userId){
        return createFarmUseCase.handle(farmRequestDTO, userId);
    }

}
