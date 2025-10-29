package com.terrasync.backend.dto.farm;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record FarmRequestDTO(

        @NotBlank(message = "Name is required")
        @Size(max = 100, min = 2, message = "Name must be between 2 and 100 characters")
        @Pattern(regexp = "^[a-zA-Z0-9\\s\\-\\.\\,]*$")  // So permite letras, numeros, espaços, hifen, ponto e virgula
        String name,

        @Positive
        BigDecimal sizeInHectares,

        @NotBlank(message = "Country is required")
        String country,

        @NotBlank(message = "State is required")
        String state,

        @NotBlank(message = "City is required")
        String city,

        @Valid
        GeolocationDTO geolocation
) {


}
