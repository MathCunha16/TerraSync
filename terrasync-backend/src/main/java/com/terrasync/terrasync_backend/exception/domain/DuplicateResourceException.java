package com.terrasync.terrasync_backend.exception.domain;

public class DuplicateResourceException extends RuntimeException {
    public DuplicateResourceException(String message) {
        super(message);
    }
}
