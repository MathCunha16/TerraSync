package com.terrasync.backend.service.cropType;

import com.terrasync.backend.dto.cropType.CropTypeRequestDTO;
import com.terrasync.backend.dto.cropType.CropTypeResponseDTO;
import com.terrasync.backend.service.cropType.useCases.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CropTypeServices {

    private final CreateCropTypeUseCase createCropTypeUseCase;
    private final FindAllCropTypesUseCase findAllCropTypesUseCase;
    private final FindCropTypeByIdUseCase findCropTypeByIdUseCase;
    private final UpdateCropTypeUseCase updateCropTypeUseCase;
    private final DeleteCropTypeUseCase deleteCropTypeUseCase;

    @Autowired
    public CropTypeServices(CreateCropTypeUseCase createCropTypeUseCase, FindAllCropTypesUseCase findAllCropTypesUseCase,
                            FindCropTypeByIdUseCase findCropTypeByIdUseCase, UpdateCropTypeUseCase updateCropTypeUseCase,
                            DeleteCropTypeUseCase deleteCropTypeUseCase) {
        this.createCropTypeUseCase = createCropTypeUseCase;
        this.findAllCropTypesUseCase = findAllCropTypesUseCase;
        this.findCropTypeByIdUseCase = findCropTypeByIdUseCase;
        this.updateCropTypeUseCase = updateCropTypeUseCase;
        this.deleteCropTypeUseCase = deleteCropTypeUseCase;
    }

    // Metodo para criar um novo crop type
    public CropTypeResponseDTO createNewCropType(CropTypeRequestDTO cropTypeRequestDTO) {
        return createCropTypeUseCase.handle(cropTypeRequestDTO);
    }

    // Metodo para buscar todos os crop types
    public List<CropTypeResponseDTO> findAllCropTypes() {
        return findAllCropTypesUseCase.handle();
    }

    // Metodo para buscar um crop type pelo ID
    public CropTypeResponseDTO findCropTypeById(Long id) {
        return findCropTypeByIdUseCase.handle(id);
    }

    // Metodo para atualizar crop type
    public CropTypeResponseDTO updateCropType(Long id, CropTypeRequestDTO dto){
        return updateCropTypeUseCase.handle(id, dto);
    }

    // Metodo para deletar um croptype pelo ID'
    public void deleteCropType(Long id) {
        deleteCropTypeUseCase.handle(id);
    }

}
