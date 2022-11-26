package com.supdevinci.tournamentmanager.api.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

/**
 * Player detail DTO.
 */
@Data
@Builder
public class TeamDetailDto {

    Long id;
    String teamName;
    List<PlayerDto> players;
    Integer nbVictories;

}
