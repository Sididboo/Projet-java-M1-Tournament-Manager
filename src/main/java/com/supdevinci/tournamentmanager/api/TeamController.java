package com.supdevinci.tournamentmanager.api;

import java.util.List;
import java.util.Optional;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.supdevinci.tournamentmanager.api.dto.TeamDetailDto;
import com.supdevinci.tournamentmanager.api.dto.TeamDto;
import com.supdevinci.tournamentmanager.api.exception.ResourceNotFoundException;
import com.supdevinci.tournamentmanager.api.mapper.TeamMapper;
import com.supdevinci.tournamentmanager.model.Team;
import com.supdevinci.tournamentmanager.service.TeamService;
import com.supdevinci.tournamentmanager.service.TournamentService;

import lombok.RequiredArgsConstructor;

/**
 * Team controller.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/v1/team", produces = MediaType.APPLICATION_JSON_VALUE)
public class TeamController {

    private final TeamService teamService;
    private final TournamentService tournamentService;
    private final TeamMapper mapper;

    /**
     * Get all teams.
     * 
     * @return the list of teams
     */
    @GetMapping
    public ResponseEntity<List<TeamDto>> getTeams() {
        return ResponseEntity
                .ok(teamService
                        .findAllTeams()
                        .stream()
                        .map(mapper::mapToDto)
                        .toList());
    }

    /**
     * Get team details.
     * 
     * @param id
     * @return the details of one team
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<TeamDetailDto> getPlayerById(@PathVariable(name = "id") Long id) {
        Optional<Team> team = teamService.findTeamById(id);

        if (!team.isPresent()) {
            throw new ResourceNotFoundException("Team", id);
        } else {
            Integer nbVictories = tournamentService.findNbVictoriesOfTeam(team.get());

            return ResponseEntity.ok(mapper.mapToDetailDto(team.get(), nbVictories));
        }
    }

}
