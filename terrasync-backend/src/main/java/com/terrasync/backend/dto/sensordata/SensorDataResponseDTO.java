package com.terrasync.backend.dto.sensordata;

import java.time.OffsetDateTime;
import java.util.Map;

public record SensorDataResponseDTO(
        Long id,
        Long sensorId,
        Map<String, Object> payload,
        OffsetDateTime readAt
) {
}
