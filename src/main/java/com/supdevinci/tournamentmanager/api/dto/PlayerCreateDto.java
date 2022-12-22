package com.supdevinci.tournamentmanager.api.dto;

import javax.validation.constraints.NotBlank;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlayerCreateDto {

    @NotBlank
    String pseudo;
    @NotBlank
    String postalAddress;

}
