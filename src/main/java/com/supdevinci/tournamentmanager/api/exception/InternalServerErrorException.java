package com.supdevinci.tournamentmanager.api.exception;

/**
 * Internal Server Error exception custom.
 */
public class InternalServerErrorException extends RuntimeException {

    public InternalServerErrorException(String indication) {
        super(indication);
    }
}