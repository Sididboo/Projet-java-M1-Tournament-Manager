package com.supdevinci.tournamentmanager.api.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Team DTO.
 */
@Data
@Builder
public class TeamDto {

    public Long id;

    @NotEmpty(message = "Team name may not be empty")
    @NotNull(message = "Team name may not be null")
    public String teamName;

    // Mapping ignore if null value
    @JsonInclude(Include.NON_NULL)
    public List<PlayerDto> players;

}
