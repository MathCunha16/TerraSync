package com.terrasync.backend.service.farm.useCases;

import com.terrasync.backend.dto.farm.FarmResponseDTO;
import com.terrasync.backend.entity.Farm;
import com.terrasync.backend.exception.domain.ResourceNotFoundException;
import com.terrasync.backend.mapper.FarmMapper;
import com.terrasync.backend.repository.FarmRepository;
import com.terrasync.backend.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FindAllFarmsByUserUseCase {

    private final Logger logger =  LoggerFactory.getLogger(this.getClass());
    private final FarmRepository farmRepository;
    private final UserRepository userRepository;
    private final FarmMapper farmMapper;

    @Autowired
    public FindAllFarmsByUserUseCase(FarmMapper farmMapper, FarmRepository farmRepository, UserRepository userRepository) {
        this.farmMapper = farmMapper;
        this.farmRepository = farmRepository;
        this.userRepository = userRepository;
    }

    public List<FarmResponseDTO> handle(Long userId) {
        logger.info("--------- Trying to Find All Farms for User ID---------");

        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User with ID " + userId + " not found.");
        }

        List<Farm> farms = farmRepository.findAllByUser_IdAndIsActiveTrue(userId);
        return farmMapper.toResponseDTOList(farms);
    }
}
