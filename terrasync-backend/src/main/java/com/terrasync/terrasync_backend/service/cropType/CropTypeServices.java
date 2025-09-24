package com.terrasync.terrasync_backend.service.cropType;

import com.terrasync.terrasync_backend.dto.CropTypeRequestDTO;
import com.terrasync.terrasync_backend.dto.CropTypeResponseDTO;
import com.terrasync.terrasync_backend.service.cropType.useCases.CreateCropTypeUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CropTypeServices {

    private final CreateCropTypeUseCase createCropTypeUseCase;

    @Autowired
    public CropTypeServices(CreateCropTypeUseCase createCropTypeUseCase) {
        this.createCropTypeUseCase = createCropTypeUseCase;
    }

    public CropTypeResponseDTO createNewCropType(CropTypeRequestDTO cropTypeRequestDTO) {
        return createCropTypeUseCase.handle(cropTypeRequestDTO);
    }

}
