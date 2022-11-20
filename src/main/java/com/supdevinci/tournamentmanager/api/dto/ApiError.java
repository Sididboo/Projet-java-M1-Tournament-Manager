package com.supdevinci.tournamentmanager.api.dto;

import lombok.Builder;
import lombok.Data;

/**
 * Api error DTO.
 */
@Data
@Builder
public class ApiError {
    private Integer code;
    private String status;
    private String message;
    private ErrorCodeEnum error;
}
