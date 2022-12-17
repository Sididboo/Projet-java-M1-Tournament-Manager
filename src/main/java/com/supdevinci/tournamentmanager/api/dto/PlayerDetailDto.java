package com.supdevinci.tournamentmanager.api.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Player detail DTO.
 */
@Data
@Builder
public class PlayerDetailDto {

    public Long id;

    @NotEmpty(message = "Pseudo may not be empty")
    @NotNull(message = "Pseudo may not be null")
    public String pseudo;

    public String postalAddress;

    public List<TeamDto> teams;

}
