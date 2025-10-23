package com.terrasync.terrasyncBackend.dto.crop;

import com.terrasync.terrasyncBackend.dto.cropType.CropTypeResponseDTO;
import com.terrasync.terrasyncBackend.entity.enums.CropStatus;

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
