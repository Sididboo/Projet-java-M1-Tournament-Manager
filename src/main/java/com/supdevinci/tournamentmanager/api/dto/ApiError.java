package com.supdevinci.tournamentmanager.api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiError {
    private Integer code;
    private String status;
    private String message;
    private ErrorCodeEnum error;
}
