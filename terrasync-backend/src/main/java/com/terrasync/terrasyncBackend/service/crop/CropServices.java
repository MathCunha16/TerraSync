package com.terrasync.terrasyncBackend.service.crop;

import com.terrasync.terrasyncBackend.dto.crop.CropRequestDTO;
import com.terrasync.terrasyncBackend.dto.crop.CropResponseDTO;
import com.terrasync.terrasyncBackend.service.crop.useCases.CreateCropUseCase;
import org.springframework.stereotype.Service;

@Service
public class CropServices {

    private final CreateCropUseCase createCropUseCase;

    public CropServices(CreateCropUseCase createCropUseCase) {
        this.createCropUseCase = createCropUseCase;
    }

    public CropResponseDTO createCrop(CropRequestDTO dto, Long userId) {
        return createCropUseCase.handle(dto, userId);
    }

}
