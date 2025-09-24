package com.terrasync.terrasync_backend.dto;

import java.time.OffsetDateTime;

public record CropTypeResponseDTO(Long id, String name, OffsetDateTime createdAt, OffsetDateTime updatedAt) {
}
