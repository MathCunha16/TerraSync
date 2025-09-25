package com.terrasync.terrasync_backend.exception;

import com.terrasync.terrasync_backend.exception.domain.DuplicateResourceException;
import com.terrasync.terrasync_backend.exception.domain.ResourceNotFoundException;
import com.terrasync.terrasync_backend.exception.dto.ExceptionResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    // Handler específico para o erro 404 Not Found
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponseDto> handleResourceNotFoundException(ResourceNotFoundException ex) {
        var response = new ExceptionResponseDto(
                ex.getMessage(),
                HttpStatus.NOT_FOUND,
                Collections.emptyList(), // Sem erros de validação neste caso
                OffsetDateTime.now()
        );
        return new ResponseEntity<>(response, response.statusCode());
    }

    // Handler para tratar erros com duplicatas
    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ExceptionResponseDto> handleDuplicateResourceException(DuplicateResourceException ex) {
        var response = new ExceptionResponseDto(
                ex.getMessage(),
                HttpStatus.CONFLICT, // <-- HTTP 409
                Collections.emptyList(),
                OffsetDateTime.now()
        );
        return new ResponseEntity<>(response, response.statusCode());
    }


    // Captura todas as exceções que não são especificadas
    // Esse handle deve ser sempre o útlimo.
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponseDto> handleInternalServerError(Exception ex) {
        var response = new ExceptionResponseDto(
                "An unexpected error occurred. Please try again later.",
                HttpStatus.INTERNAL_SERVER_ERROR,
                new ArrayList<>(),
                OffsetDateTime.now()
        );

        return ResponseEntity.status(response.statusCode().value()).body(response);
    }
}
