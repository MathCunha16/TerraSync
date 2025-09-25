package com.terrasync.terrasync_backend.exception.dto;

public record ValidationErrorDto(
        String message,
        String field
) {
}
