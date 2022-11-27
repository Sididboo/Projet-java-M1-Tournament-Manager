package com.supdevinci.tournamentmanager.api.mapper;

import org.mapstruct.Mapper;

import com.supdevinci.tournamentmanager.api.dto.TeamDetailDto;
import com.supdevinci.tournamentmanager.api.dto.TeamDto;
import com.supdevinci.tournamentmanager.model.Team;

/**
 * Team mapper.
 */
@Mapper(componentModel = "spring")
public interface TeamMapper {

    TeamDto mapToDto(Team team);

    TeamDetailDto mapToDetailDto(Team team, Integer nbVictories);

}
