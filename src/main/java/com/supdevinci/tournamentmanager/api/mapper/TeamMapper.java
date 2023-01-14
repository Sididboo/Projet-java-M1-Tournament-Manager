package com.supdevinci.tournamentmanager.api.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.supdevinci.tournamentmanager.api.dto.TeamCreateDto;
import com.supdevinci.tournamentmanager.api.dto.TeamDetailDto;
import com.supdevinci.tournamentmanager.api.dto.TeamDto;
import com.supdevinci.tournamentmanager.model.Player;
import com.supdevinci.tournamentmanager.model.Team;

/**
 * Team mapper.
 */
@Mapper(componentModel = "spring")
public interface TeamMapper {

    TeamDto mapToDto(Team team);

    TeamDetailDto mapToDetailDto(Team team, Integer nbVictories);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tournaments", ignore = true)
    Team mapToEntity(TeamCreateDto teamCreateDto, List<Player> players);
}
