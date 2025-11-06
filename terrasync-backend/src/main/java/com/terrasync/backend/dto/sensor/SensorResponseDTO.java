package com.terrasync.backend.dto.sensor;

import com.terrasync.backend.entity.enums.SensorType;

import java.time.Instant;
import java.time.LocalDate;
import java.time.OffsetDateTime;

public record SensorResponseDTO(
        Long id,
        Long cropId,
        String name,
        String deviceUuid,
        SensorType type,
        Boolean status,
        Instant lastHeartbeat,
        LocalDate installDate,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) {
}
