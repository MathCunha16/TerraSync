package com.terrasync.terrasync_backend.dto.cropType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CropTypeRequestDTO(
        @NotBlank(message = "Name is required")
        @Size(max = 100, min = 2, message = "Name must be between 2 and 100 characters")
        @Pattern(regexp = "^[a-zA-Z0-9\\s\\-\\.\\,]*$")  // So permite letras, numeros, espaços, hifen, ponto e virgula
        String name) {
}
