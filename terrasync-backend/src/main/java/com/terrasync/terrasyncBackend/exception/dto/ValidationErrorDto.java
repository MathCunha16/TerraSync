package com.terrasync.terrasyncBackend.exception.dto;

public record ValidationErrorDto(
        String message,
        String field
) {
}
