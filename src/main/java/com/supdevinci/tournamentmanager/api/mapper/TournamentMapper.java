package com.supdevinci.tournamentmanager.api.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.supdevinci.tournamentmanager.api.dto.TeamDto;
import com.supdevinci.tournamentmanager.api.dto.TournamentCreateDto;
import com.supdevinci.tournamentmanager.api.dto.TournamentDetailDto;
import com.supdevinci.tournamentmanager.api.dto.TournamentDto;
import com.supdevinci.tournamentmanager.model.Team;
import com.supdevinci.tournamentmanager.model.Tournament;

/**
 * Tournament mapper.
 */
@Mapper(componentModel = "spring")
public interface TournamentMapper {

    TournamentDto mapToDto(Tournament tournament);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "state", ignore = true)
    @Mapping(target = "winningTeam", ignore = true)
    Tournament mapToEntity(TournamentCreateDto tournamentCreateDto, List<Team> teams);

    @Mapping(target = "nameState", source = "state.name")
    TournamentDetailDto mapToDetailDto(Tournament tournament);

    // This mapper will be automatically detected if another mapper needs to map the
    // same type
    @Mapping(target = "players", ignore = true)
    TeamDto mapToTeamDto(Team team);
}
