package com.terrasync.terrasyncBackend.dto.crop;

import com.terrasync.terrasyncBackend.entity.enums.CropStatus;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record CropRequestDTO(
        @NotNull(message = "Farm ID is required")
        Long farmId,

        @NotNull(message = "Crop Type ID is required")
        Long cropTypeId,

        @NotBlank(message = "Name is required")
        @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
        @Pattern(regexp = "^[a-zA-Z0-9\\s\\-\\.\\,]*$")
        String name,

        LocalDate plantingDate,

        @FutureOrPresent(message = "Harvest date must be in the present or future")
        LocalDate harvestDate,

        @NotNull(message = "Status is required")
        CropStatus status
) {
}
