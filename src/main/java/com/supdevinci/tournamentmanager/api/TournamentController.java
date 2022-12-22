package com.supdevinci.tournamentmanager.api;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.supdevinci.tournamentmanager.api.dto.TournamentCreateDto;
import com.supdevinci.tournamentmanager.api.dto.TournamentDetailDto;
import com.supdevinci.tournamentmanager.api.exception.InternalServerErrorException;
import com.supdevinci.tournamentmanager.api.mapper.TournamentMapper;
import com.supdevinci.tournamentmanager.model.State;
import com.supdevinci.tournamentmanager.model.Team;
import com.supdevinci.tournamentmanager.model.Tournament;
import com.supdevinci.tournamentmanager.service.StateService;
import com.supdevinci.tournamentmanager.service.TeamService;
import com.supdevinci.tournamentmanager.service.TournamentService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/v1/tournament", produces = MediaType.APPLICATION_JSON_VALUE)
public class TournamentController {

    private final TournamentService tournamentService;
    private final TeamService teamService;
    private final StateService stateService;
    private final TournamentMapper mapper;

    /**
     * Create team.
     * 
     * @return the details of team created
     */
    @PostMapping
    public ResponseEntity<TournamentDetailDto> createTournament(
            @RequestBody @Valid TournamentCreateDto tournamentCreateDto) {
        // Recovery of the teams with the list of identifiers
        List<Team> teams = teamService.findTeamsByIds(tournamentCreateDto.getTeamIds());

        Tournament tournament = mapper.mapToEntity(tournamentCreateDto, teams);

        // The "Planned" state is assigned by default when a tournament is created
        Optional<State> state = stateService.findStateById(1L);
        if (state.isPresent()) {
            tournament.setState(state.get());
        } else {
            throw new InternalServerErrorException("It is possible that the tournament states are not created");
        }

        Tournament newTournament = tournamentService.saveTournament(tournament);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.mapToDetailDto(newTournament));
    }

}
