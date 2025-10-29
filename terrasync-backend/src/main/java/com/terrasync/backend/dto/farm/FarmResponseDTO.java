package com.terrasync.backend.dto.farm;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record FarmResponseDTO(
        Long id,
        Long userId,
        String name,
        BigDecimal sizeInHectares,
        String state,
        String city,
        String country,
        GeolocationDTO geolocation,
        boolean isActive,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) {
}
