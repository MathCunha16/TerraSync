package com.terrasync.backend.dto.sensor;

import com.terrasync.backend.entity.enums.SensorType;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record SensorRequestDTO(
        @NotNull(message = "Crop ID is required")
        Long cropId,

        @NotBlank(message = "Name is required")
        @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
        @Pattern(regexp = "^[a-zA-Z0-9\\s\\-\\.\\,]*$")
        String name,

        @NotBlank(message = "Device UUID is required")
        @Size(min = 5, max = 255, message = "Device UUID must be between 5 and 255 characters")
        String deviceUuid,

        @NotNull(message = "Sensor type is required")
        SensorType type,

        Boolean status,

        LocalDate installDate
) {
}
