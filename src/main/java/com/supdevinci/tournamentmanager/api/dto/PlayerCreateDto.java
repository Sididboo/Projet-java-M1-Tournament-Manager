package com.supdevinci.tournamentmanager.api.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class PlayerCreateDto {

    @NotEmpty(message = "Pseudo may not be empty")
    @NotNull(message = "Pseudo may not be null")
    public String pseudo;

    public String postalAddress;

}
