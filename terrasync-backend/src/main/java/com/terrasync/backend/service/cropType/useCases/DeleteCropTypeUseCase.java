package com.terrasync.backend.service.cropType.useCases;

import com.terrasync.backend.entity.CropType;
import com.terrasync.backend.exception.domain.ResourceNotFoundException;
import com.terrasync.backend.repository.CropTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteCropTypeUseCase {

    private final Logger logger = LoggerFactory.getLogger(DeleteCropTypeUseCase.class);
    private final CropTypeRepository cropTypeRepository;

    @Autowired
    public DeleteCropTypeUseCase(CropTypeRepository cropTypeRepository) {
        this.cropTypeRepository = cropTypeRepository;
    }

    public void handle(Long id) {
        logger.info("--------- Trying to Delete Crop Type ---------");

        CropType cropType = cropTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Crop Type with ID " + id + " not found."));

        // TODO: Adicionar validação para impedir exclusão se CropType estiver em uso
        // if (cropRepository.existsByCropTypeId(cropType.getId())) {
        //     throw new BadRequestException("Cannot delete: Crop Type is in use.");
        // }

        cropTypeRepository.delete(cropType);
    }
}
