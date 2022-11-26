package com.supdevinci.tournamentmanager.api;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.supdevinci.tournamentmanager.api.dto.TeamDto;
import com.supdevinci.tournamentmanager.api.mapper.TeamMapper;
import com.supdevinci.tournamentmanager.model.Team;
import com.supdevinci.tournamentmanager.service.TeamService;

import lombok.RequiredArgsConstructor;

/**
 * Team controller.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/v1/team", produces = MediaType.APPLICATION_JSON_VALUE)
public class TeamController {

    private final TeamService teamService;
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
                        .map((Team team) -> mapper.mapToDto(team))
                        .toList());
    }

}
