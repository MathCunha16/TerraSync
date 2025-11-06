package com.terrasync.backend.service.crop;

import com.terrasync.backend.dto.crop.CropRequestDTO;
import com.terrasync.backend.dto.crop.CropResponseDTO;
import com.terrasync.backend.service.crop.useCases.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CropServices {

    private final CreateCropUseCase createCropUseCase;
    private final FindCropByIdUseCase findCropByIdUseCase;
    private final FindAllCropsByFarmUseCase findAllCropsByFarmUseCase;
    private final UpdateCropUseCase updateCropUseCase;
    private final DeleteCropUseCase deleteCropUseCase;


    public CropServices(CreateCropUseCase createCropUseCase,
                        FindCropByIdUseCase findCropByIdUseCase,
                        FindAllCropsByFarmUseCase findAllCropsByFarmUseCase,
                        UpdateCropUseCase updateCropUseCase,
                        DeleteCropUseCase deleteCropUseCase) {
        this.createCropUseCase = createCropUseCase;
        this.findCropByIdUseCase = findCropByIdUseCase;
        this.findAllCropsByFarmUseCase = findAllCropsByFarmUseCase;
        this.updateCropUseCase = updateCropUseCase;
        this.deleteCropUseCase = deleteCropUseCase;
    }

    public CropResponseDTO createCrop(CropRequestDTO dto, Long userId) {
        return createCropUseCase.handle(dto, userId);
    }

    public CropResponseDTO getCropById(Long cropId, Long userId) {
        return findCropByIdUseCase.handle(cropId, userId);
    }

    public List<CropResponseDTO> getAllCropsByFarm(Long farmId, Long userId) {
        return findAllCropsByFarmUseCase.handle(farmId, userId);
    }

    public CropResponseDTO updateCrop(Long cropId, Long userId, CropRequestDTO requestDTO) {
        return updateCropUseCase.handle(cropId, userId, requestDTO);
    }

    public String deleteCrop(Long cropId, Long userId) {
        return deleteCropUseCase.handle(cropId, userId);
    }
}
