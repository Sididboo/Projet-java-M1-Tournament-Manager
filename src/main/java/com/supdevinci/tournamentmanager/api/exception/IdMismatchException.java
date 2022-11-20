package com.supdevinci.tournamentmanager.api.exception;

/**
 * Bad request exception custom.
 */
public class IdMismatchException extends RuntimeException {

    public IdMismatchException(Long pathId, Long bodyId) {
        super("The request body's id [" + bodyId + "] and the path's id [" + pathId + "] are not the same");
    }
}
