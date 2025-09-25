package com.terrasync.terrasync_backend.mapper;

import com.terrasync.terrasync_backend.dto.cropType.CropTypeRequestDTO;
import com.terrasync.terrasync_backend.dto.cropType.CropTypeResponseDTO;
import com.terrasync.terrasync_backend.entity.CropType;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CropTypeMapper {

    // Converte Entidade -> DTO de Resposta
    CropTypeResponseDTO toResponseDTO(CropType cropType);

    // Converte Lista de Entidades -> Lista de DTOs de Resposta
    List<CropTypeResponseDTO> toResponseDTOList(List<CropType> cropTypes);

    // Converte DTO de Requisição -> Entidade (pra criação)
    CropType toEntity(CropTypeRequestDTO cropTypeRequestDTO);

    // Atualiza uma Entidade existente a partir de um DTO (pra atualização)
    void updateFromDTO(CropTypeRequestDTO dto, @MappingTarget CropType entity);

}
