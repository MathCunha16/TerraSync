package com.terrasync.backend.dto.sensordata;

import jakarta.validation.constraints.NotNull;

import java.time.OffsetDateTime;
import java.util.Map;

public record SensorDataRequestDTO(
        @NotNull(message = "Sensor ID is required")
        Long sensorId,

        @NotNull(message = "Payload is required")
        Map<String, Object> payload,

        @NotNull(message = "Read at timestamp is required")
        OffsetDateTime readAt
) {
}
