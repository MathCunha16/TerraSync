package com.terrasync.terrasync_backend.service.farm.useCases;

import com.terrasync.terrasync_backend.dto.farm.FarmRequestDTO;
import com.terrasync.terrasync_backend.dto.farm.FarmResponseDTO;
import com.terrasync.terrasync_backend.entity.Farm;
import com.terrasync.terrasync_backend.exception.domain.DuplicateResourceException;
import com.terrasync.terrasync_backend.exception.domain.ResourceNotFoundException;
import com.terrasync.terrasync_backend.mapper.FarmMapper;
import com.terrasync.terrasync_backend.repository.FarmRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UpdateFarmUseCase {

    private Logger logger = org.slf4j.LoggerFactory.getLogger(UpdateFarmUseCase.class);
    private final FarmRepository farmRepository;
    private final FarmMapper farmMapper;

    @Autowired
    public UpdateFarmUseCase(FarmRepository farmRepository, FarmMapper farmMapper) {
        this.farmRepository = farmRepository;
        this.farmMapper = farmMapper;
    }

    public FarmResponseDTO handle(Long farmId, Long userId, FarmRequestDTO dto) {
        logger.info("--------- Trying to Update Farm with ID ---------");

        Farm farmToUpdate = farmRepository.findByIdAndUser_Id(farmId, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Farm with ID " + farmId + " not found for this user."));

        // Valida se o novo nome já está em uso por OUTRA fazenda.
        validateNameUniqueness(dto.name(), userId, farmId);
        farmMapper.updateFromDTO(dto, farmToUpdate);
        Farm updatedFarm = farmRepository.save(farmToUpdate);
        return farmMapper.toResponseDTO(updatedFarm);
    }

    // TODO: Validar essa lógica com unit test urgente!
    private void validateNameUniqueness(String name, Long userId, Long currentFarmId) {
        Optional<Farm> existingFarm = farmRepository.findByNameIgnoreCaseAndUser_Id(name, userId);

        // Se uma fazenda com esse nome existe E o seu 'ID' é DIFERENTE do que estamos a atualizar, então é um conflito.
        if (existingFarm.isPresent() && !existingFarm.get().getId().equals(currentFarmId)) {
            throw new DuplicateResourceException("User already has a farm with name '" + name + "'.");
        }
    }

}
