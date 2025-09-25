package com.terrasync.terrasync_backend.service.cropType.useCases;

import com.terrasync.terrasync_backend.entity.CropType;
import com.terrasync.terrasync_backend.exception.domain.ResourceNotFoundException;
import com.terrasync.terrasync_backend.mapper.CropTypeMapper;
import com.terrasync.terrasync_backend.repository.CropTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteCropTypeUseCase {

    private final Logger logger = LoggerFactory.getLogger(DeleteCropTypeUseCase.class);
    private final CropTypeRepository cropTypeRepository;
    private final CropTypeMapper cropTypeMapper;

    @Autowired
    public DeleteCropTypeUseCase(CropTypeRepository cropTypeRepository, CropTypeMapper cropTypeMapper) {
        this.cropTypeRepository = cropTypeRepository;
        this.cropTypeMapper = cropTypeMapper;
    }

    // TODO adicionar verificação se não está linkado a uma CROP
    public void handle(Long id) {
        logger.info("--------- Trying to Delete Crop Type ---------");

        // Busca a entidade ou lança 404. Mais eficiente que o existsById do JPA.
        CropType cropTypeToUpdate = cropTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Crop Type with ID " + id + " not found."));

        cropTypeRepository.deleteById(id);
    }
}
