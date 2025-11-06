package com.terrasync.backend.mapper;

import com.terrasync.backend.dto.sensor.SensorRequestDTO;
import com.terrasync.backend.dto.sensor.SensorResponseDTO;
import com.terrasync.backend.entity.Sensor;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SensorMapper {

    @Mapping(source = "crop.id", target = "cropId")
    SensorResponseDTO toResponseDTO(Sensor sensor);

    List<SensorResponseDTO> toResponseDTOList(List<Sensor> sensors);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "crop", ignore = true)
    @Mapping(target = "lastHeartbeat", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Sensor toEntity(SensorRequestDTO sensorRequestDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "crop", ignore = true)
    @Mapping(target = "lastHeartbeat", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateFromDTO(SensorRequestDTO dto, @MappingTarget Sensor entity);
}
