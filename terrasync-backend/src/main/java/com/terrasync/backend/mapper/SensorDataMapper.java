package com.terrasync.backend.mapper;

import com.terrasync.backend.dto.sensordata.SensorDataRequestDTO;
import com.terrasync.backend.dto.sensordata.SensorDataResponseDTO;
import com.terrasync.backend.entity.SensorData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Mapper(componentModel = "spring")
public interface SensorDataMapper {

    @Mapping(source = "sensor.id", target = "sensorId")
    SensorDataResponseDTO toResponseDTO(SensorData sensorData);

    List<SensorDataResponseDTO> toResponseDTOList(List<SensorData> sensorDataList);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "sensor", ignore = true)
    SensorData toEntity(SensorDataRequestDTO sensorDataRequestDTO);

    default OffsetDateTime map(Instant instant) {
        if (instant == null) {
            return null;
        }
        return instant.atOffset(ZoneOffset.UTC); // Assuming UTC for Instant storage
    }

    default Instant map(OffsetDateTime offsetDateTime) {
        if (offsetDateTime == null) {
            return null;
        }
        return offsetDateTime.toInstant();
    }

}
