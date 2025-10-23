package com.terrasync.terrasyncBackend.service.farm;

import com.terrasync.terrasyncBackend.dto.farm.FarmRequestDTO;
import com.terrasync.terrasyncBackend.dto.farm.FarmResponseDTO;
import com.terrasync.terrasyncBackend.service.farm.useCases.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FarmServices {

    private final CreateFarmUseCase createFarmUseCase;
    private final FindAllFarmsByUserUseCase findAllFarmsByUserUseCase;
    private final FindFarmByIdUseCase findFarmByIdUseCase;
    private final UpdateFarmUseCase updateFarmUseCase;
    private final DeleteFarmUseCase deleteFarmUseCase;

    @Autowired
    public FarmServices (CreateFarmUseCase createFarmUseCase, FindAllFarmsByUserUseCase findAllFarmsByUserUseCase,
                         FindFarmByIdUseCase findFarmByIdUseCase, UpdateFarmUseCase updateFarmUseCase,
                         DeleteFarmUseCase deleteFarmUseCase) {
        this.createFarmUseCase = createFarmUseCase;
        this.findAllFarmsByUserUseCase =  findAllFarmsByUserUseCase;
        this.findFarmByIdUseCase = findFarmByIdUseCase;
        this.updateFarmUseCase = updateFarmUseCase;
        this.deleteFarmUseCase = deleteFarmUseCase;
    }

    // Metodo para cirar nova fazenda
    public FarmResponseDTO createNewFarm(FarmRequestDTO farmRequestDTO, Long userId){
        return createFarmUseCase.handle(farmRequestDTO, userId);
    }

    // Metodo para encontrar todas as farms por 'User' com 'ID' ativo
    public List<FarmResponseDTO> findAllFarmsByUserId(Long userId) {
        return findAllFarmsByUserUseCase.handle(userId);
    }

    // Metodo para buscar farm pelo 'id', verificando o 'user'
    public FarmResponseDTO findFarmById(Long farmId, Long userId){
        return findFarmByIdUseCase.handle(farmId, userId);
    }

    // Metodo para atualizar farm
    public FarmResponseDTO updateFarm(Long farmId, Long userId, FarmRequestDTO farmRequestDTO){
        return updateFarmUseCase.handle(farmId, userId, farmRequestDTO);
    }

    // Metodo para desativar farm
    public void deleteFarm(Long farmId, Long userId){
        deleteFarmUseCase.handle(farmId, userId);
    }
}
