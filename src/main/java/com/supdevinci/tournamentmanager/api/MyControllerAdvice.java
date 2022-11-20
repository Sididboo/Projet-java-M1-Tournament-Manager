package com.supdevinci.tournamentmanager.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import com.supdevinci.tournamentmanager.api.dto.ApiError;
import com.supdevinci.tournamentmanager.api.dto.ErrorCodeEnum;
import com.supdevinci.tournamentmanager.api.exception.IdMismatchException;
import com.supdevinci.tournamentmanager.api.exception.ResourceNotFoundException;

/**
 * Controller advice.
 * Handles http errors.
 */
public class MyControllerAdvice {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = { ResourceNotFoundException.class })
    protected ApiError handleNotFound(ResourceNotFoundException ex, WebRequest request) {
        return ApiError.builder()
                .code(HttpStatus.NOT_FOUND.value())
                .status(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(ex.getMessage())
                .error(ErrorCodeEnum.RESOURCE_NOT_FOUND)
                .build();
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = { IdMismatchException.class })
    protected ApiError handleBadRequest(IdMismatchException ex, WebRequest request) {
        return ApiError.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(ex.getMessage())
                .error(ErrorCodeEnum.ID_MISMATCH)
                .build();
    }

}
