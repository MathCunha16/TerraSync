package com.terrasync.backend.exception.dto;

public record ValidationErrorDto(
        String message,
        String field
) {
}
