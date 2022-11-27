package com.supdevinci.tournamentmanager.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.supdevinci.tournamentmanager.api.dto.PlayerDetailDto;
import com.supdevinci.tournamentmanager.api.dto.PlayerDto;
import com.supdevinci.tournamentmanager.api.dto.TeamDto;
import com.supdevinci.tournamentmanager.model.Player;
import com.supdevinci.tournamentmanager.model.Team;

/**
 * Player mapper.
 */
@Mapper(componentModel = "spring")
public interface PlayerMapper {

    PlayerDto mapToDto(Player player);

    PlayerDetailDto mapToDetailDto(Player player);

    // This mapper will be automatically detected if another mapper needs to map the same type
    @Mapping(target = "players", ignore = true)
    TeamDto mapToTeamDto(Team team);

}