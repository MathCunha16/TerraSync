package com.terrasync.terrasyncBackend.mapper;

import com.terrasync.terrasyncBackend.dto.cropType.CropTypeRequestDTO;
import com.terrasync.terrasyncBackend.dto.cropType.CropTypeResponseDTO;
import com.terrasync.terrasyncBackend.entity.CropType;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CropTypeMapper {

    // Converte Entidade → DTO de Resposta
    CropTypeResponseDTO toResponseDTO(CropType cropType);

    // Converte Lista de Entidades → Lista de DTOs de Resposta
    List<CropTypeResponseDTO> toResponseDTOList(List<CropType> cropTypes);

    // Converte DTO de Requisição → Entidade (para criação)
    CropType toEntity(CropTypeRequestDTO cropTypeRequestDTO);

    // Atualiza uma Entidade existente a partir de um DTO (para atualização)
    void updateFromDTO(CropTypeRequestDTO dto, @MappingTarget CropType entity);

}
