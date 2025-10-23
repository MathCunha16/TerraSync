package com.terrasync.terrasyncBackend.mapper;

import com.terrasync.terrasyncBackend.dto.crop.CropRequestDTO;
import com.terrasync.terrasyncBackend.dto.crop.CropResponseDTO;
import com.terrasync.terrasyncBackend.entity.Crop;
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
