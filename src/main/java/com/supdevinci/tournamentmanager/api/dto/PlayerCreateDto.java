package com.supdevinci.tournamentmanager.api.dto;

import javax.validation.constraints.NotBlank;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlayerCreateDto {

    @NotBlank
    private String pseudo;
    @NotBlank
    private String postalAddress;

}
