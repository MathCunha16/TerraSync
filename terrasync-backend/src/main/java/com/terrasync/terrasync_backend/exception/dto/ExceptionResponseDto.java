package com.terrasync.terrasync_backend.exception.dto;

import org.springframework.http.HttpStatus;

import java.time.OffsetDateTime;
import java.util.List;

public record ExceptionResponseDto(
        String message,
        HttpStatus statusCode,
        List<ValidationErrorDto> validationErrors,
        OffsetDateTime timestamp
) {
}
