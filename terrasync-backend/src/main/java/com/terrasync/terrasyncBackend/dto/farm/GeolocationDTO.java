package com.terrasync.terrasyncBackend.dto.farm;

import java.math.BigDecimal;

public record GeolocationDTO(
        BigDecimal latitude,
        BigDecimal longitude
) {
}
