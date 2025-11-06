package com.terrasync.backend.mapper;

import com.terrasync.backend.dto.cropType.CropTypeRequestDTO;
import com.terrasync.backend.dto.cropType.CropTypeResponseDTO;
import com.terrasync.backend.entity.CropType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CropTypeMapper {

    CropTypeResponseDTO toResponseDTO(CropType cropType);

    List<CropTypeResponseDTO> toResponseDTOList(List<CropType> cropTypes);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    CropType toEntity(CropTypeRequestDTO cropTypeRequestDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateFromDTO(CropTypeRequestDTO dto, @MappingTarget CropType entity);
}
