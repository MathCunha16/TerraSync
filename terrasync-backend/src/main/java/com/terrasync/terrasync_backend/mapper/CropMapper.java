package com.terrasync.terrasync_backend.mapper;

import com.terrasync.terrasync_backend.dto.crop.CropRequestDTO;
import com.terrasync.terrasync_backend.dto.crop.CropResponseDTO;
import com.terrasync.terrasync_backend.entity.Crop;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CropMapper {

    @Mapping(source = "farm.id", target = "farmId")
    @Mapping(source = "cropType", target = "cropType")
    CropResponseDTO toResponseDTO(Crop crop);

    List<CropResponseDTO> toResponseDTOList(List<Crop> crops);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "farm", ignore = true)
    @Mapping(target = "cropType", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Crop toEntity(CropRequestDTO cropRequestDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "farm", ignore = true)
    @Mapping(target = "cropType", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateFromDTO(CropRequestDTO dto, @MappingTarget Crop entity);
}
