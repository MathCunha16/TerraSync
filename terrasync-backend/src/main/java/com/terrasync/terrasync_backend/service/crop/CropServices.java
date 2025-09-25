package com.terrasync.terrasync_backend.service.crop;

import com.terrasync.terrasync_backend.dto.crop.CropRequestDTO;
import com.terrasync.terrasync_backend.dto.crop.CropResponseDTO;
import com.terrasync.terrasync_backend.service.crop.useCases.CreateCropUseCase;
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
