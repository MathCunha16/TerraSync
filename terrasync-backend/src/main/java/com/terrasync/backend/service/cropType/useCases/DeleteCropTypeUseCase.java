package com.terrasync.backend.service.cropType.useCases;

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

        if (!cropTypeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Crop Type with ID " + id + " not found.");
        }

        // Placeholder para validação futura (sem carregar entidade!)
        // TODO: Impedir exclusão se CropType estiver vinculado a algum Crop
        // Ex: if (cropRepository.existsByCropTypeId(id)) { throw .... }

        cropTypeRepository.deleteById(id);
    }
}
