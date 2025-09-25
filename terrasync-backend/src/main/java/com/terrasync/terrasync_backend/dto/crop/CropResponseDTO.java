package com.terrasync.terrasync_backend.dto.crop;

import com.terrasync.terrasync_backend.dto.cropType.CropTypeResponseDTO;
import com.terrasync.terrasync_backend.entity.enums.CropStatus;

import java.time.LocalDate;
import java.time.OffsetDateTime;

public record CropResponseDTO(
        Long id,
        Long farmId,
        String name,
        CropTypeResponseDTO cropType,
        LocalDate plantingDate,
        LocalDate harvestDate,
        CropStatus status,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) {
}
