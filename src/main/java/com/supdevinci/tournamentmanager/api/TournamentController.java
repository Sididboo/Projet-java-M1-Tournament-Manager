package com.supdevinci.tournamentmanager.api;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.supdevinci.tournamentmanager.api.dto.TournamentCreateDto;
import com.supdevinci.tournamentmanager.api.dto.TournamentDetailDto;
import com.supdevinci.tournamentmanager.api.dto.TournamentDto;
import com.supdevinci.tournamentmanager.api.dto.TournamentUpdateDto;
import com.supdevinci.tournamentmanager.api.exception.IdMismatchException;
import com.supdevinci.tournamentmanager.api.exception.InternalServerErrorException;
import com.supdevinci.tournamentmanager.api.exception.MissedTeamException;
import com.supdevinci.tournamentmanager.api.exception.ResourceNotFoundException;
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
     * Create tournament.
     * 
     * @param tournamentCreateDto
     * @return the details of tournament created
     */
    @PostMapping
    public ResponseEntity<TournamentDetailDto> createTournament(
            @RequestBody @Valid TournamentCreateDto tournamentCreateDto) {
        // Recovery of the teams with the list of identifiers
        List<Team> teams = teamService.findTeamsByIds(tournamentCreateDto.getTeamIds());
        Tournament tournament = mapper.mapToEntity(tournamentCreateDto, teams);

        tournament.setNumberOfParticipants((int) teams.stream().map(mapper::mapToTeamDto).count());

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

    /**
     * Get all tournament.
     *
     * @return the list of tournament
     */
    @GetMapping
    public ResponseEntity<List<TournamentDto>> getTournament() {
        return ResponseEntity
                .ok(tournamentService
                        .findAllTournament()
                        .stream()
                        .map(mapper::mapToDto)
                        .toList());
    }

    /**
     * Get tournament details.
     *
     * @param id
     * @return the details of one tournament
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<TournamentDetailDto> getTournamentById(@PathVariable(name = "id") Long id) {
        Optional<Tournament> tournament = tournamentService.findTournamentById(id);

        if (tournament.isEmpty()) {
            throw new ResourceNotFoundException("Tournament", id);
        } else {
            return ResponseEntity.ok(mapper.mapToDetailDto(tournament.get()));
        }
    }

    /**
     * Update tournament state.
     *
     * @param id tournament identifier
     * @param tournamentUpdateDto
     * @return update tournament
     */
    @PutMapping(path = "/{id}")
    public ResponseEntity<TournamentDetailDto> updateTournament(
            @PathVariable Long id,
            @Valid @RequestBody TournamentUpdateDto tournamentUpdateDto

    ) {
        // Get tournament
        Optional<Tournament> optionalTournament = tournamentService.findTournamentById(id);
        if (optionalTournament.isEmpty()) {
            throw new ResourceNotFoundException("Tournament", id);
        }

        // Id in path should be equals to tournament id in body
        if (!Objects.equals(id, tournamentUpdateDto.getId())) {
            throw new IdMismatchException(id, tournamentUpdateDto.getId());
        }

        // Get state
        Optional<State> optionalState = stateService.findStateById(tournamentUpdateDto.getStateId());
        if (!optionalState.isPresent()) {
            throw new ResourceNotFoundException("State", tournamentUpdateDto.getStateId());
        }

        Tournament tournament = optionalTournament.get();
        tournament.setTeams(optionalTournament.get().getTeams());
        tournament.setState(optionalState.get());

        // If the status identifier is 3 ("Completed") then a team is required
        if (tournamentUpdateDto.getId() == 3 && tournamentUpdateDto.getWinningTeamId() == null) {
            throw new MissedTeamException(tournamentUpdateDto.getStateId());
        }

        // Get winning team
        if (tournamentUpdateDto.getWinningTeamId() != null) {
            Optional<Team> optionalTeam = teamService.findTeamById(tournamentUpdateDto.getWinningTeamId());
            if (!optionalTeam.isPresent()) {
                throw new ResourceNotFoundException("Team", tournamentUpdateDto.getWinningTeamId());
            }

            tournament.setWinningTeam(optionalTeam.get());
        }

        Tournament updatedTournament = tournamentService.saveTournament(tournament);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.mapToDetailDto(updatedTournament));
    }

}
