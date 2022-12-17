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
public class TeamDetailDto {

    public Long id;

    @NotEmpty(message = "Team name may not be empty")
    @NotNull(message = "Team name may not be null")
    public String teamName;

    public List<PlayerDto> players;
    public Integer nbVictories;

}
