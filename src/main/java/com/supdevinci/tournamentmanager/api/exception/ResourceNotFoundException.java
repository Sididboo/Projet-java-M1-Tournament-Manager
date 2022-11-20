package com.supdevinci.tournamentmanager.api.exception;

/**
 * Resource not found exception custom.
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resourceType, Long id) {
        super(resourceType + " with id [" + id + "] not found");
    }
}
