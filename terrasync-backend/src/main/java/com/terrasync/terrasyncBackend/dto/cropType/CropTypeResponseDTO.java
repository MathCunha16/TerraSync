package com.terrasync.terrasyncBackend.dto.cropType;

import java.time.OffsetDateTime;

public record CropTypeResponseDTO(Long id, String name, OffsetDateTime createdAt, OffsetDateTime updatedAt) {
}
