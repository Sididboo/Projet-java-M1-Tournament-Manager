package com.supdevinci.tournamentmanager.api.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Player DTO.
 */
@Data
@Builder
public class PlayerDto {

    public Long id;

    @NotEmpty(message = "Pseudo may not be empty")
    @NotNull(message = "Pseudo may not be null")
    public String pseudo;

}
