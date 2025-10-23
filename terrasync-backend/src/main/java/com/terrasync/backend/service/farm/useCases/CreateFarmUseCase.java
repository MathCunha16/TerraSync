package com.terrasync.backend.service.farm.useCases;

import com.terrasync.backend.dto.farm.FarmRequestDTO;
import com.terrasync.backend.dto.farm.FarmResponseDTO;
import com.terrasync.backend.entity.Farm;
import com.terrasync.backend.entity.User;
import com.terrasync.backend.exception.domain.DuplicateResourceException;
import com.terrasync.backend.exception.domain.ResourceNotFoundException;
import com.terrasync.backend.mapper.FarmMapper;
import com.terrasync.backend.repository.FarmRepository;
import com.terrasync.backend.repository.UserRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateFarmUseCase {

    private final Logger logger = org.slf4j.LoggerFactory.getLogger(CreateFarmUseCase.class);
    private final FarmRepository farmRepository;
    private final UserRepository userRepository;
    private final FarmMapper farmMapper;

    @Autowired
    public CreateFarmUseCase(FarmRepository farmRepository, UserRepository userRepository, FarmMapper farmMapper){
        this.farmRepository = farmRepository;
        this.userRepository = userRepository;
        this.farmMapper = farmMapper;
    }

    // TODO: Corrigir erro de isActive sempre falso (Prioridade Maxima)
    public FarmResponseDTO handle(FarmRequestDTO dto, Long userId) {
        logger.info("--------- Trying to Create a new Farm for User ID: {} ---------", userId);

        if (farmRepository.existsByNameIgnoreCaseAndUser_Id(dto.name(), userId)) {
            throw new DuplicateResourceException("User already has a farm with name '" + dto.name() + "'.");
        }

        User owner = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with ID " + userId + " not found. Cannot create farm."));

        Farm newFarm = farmMapper.toEntity(dto);
        newFarm.setUser(owner);
        Farm savedFarm = farmRepository.save(newFarm);
        return farmMapper.toResponseDTO(savedFarm);
    }

}
