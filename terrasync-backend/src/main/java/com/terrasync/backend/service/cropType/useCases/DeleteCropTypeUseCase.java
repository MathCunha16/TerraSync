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

    // TODO adicionar verificação se não está linkado a uma CROP
    public void handle(Long id) {
        logger.info("--------- Trying to Delete Crop Type ---------");

        // Busca a entidade ou lança 404. Mais eficiente que o existsById do JPA.
        cropTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Crop Type with ID " + id + " not found."));

        cropTypeRepository.deleteById(id);
    }
}
