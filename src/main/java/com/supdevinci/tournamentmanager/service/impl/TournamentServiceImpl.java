package com.supdevinci.tournamentmanager.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.supdevinci.tournamentmanager.model.Team;
import com.supdevinci.tournamentmanager.model.Tournament;
import com.supdevinci.tournamentmanager.repository.TournamentRepository;
import com.supdevinci.tournamentmanager.service.TournamentService;

import lombok.RequiredArgsConstructor;

/**
 * Service to interact with the database with the table tournament.
 */
@Service
@RequiredArgsConstructor
public class TournamentServiceImpl implements TournamentService {

    private final TournamentRepository tournamentRepository;

    @Override
    public Tournament saveTournament(Tournament tournament) {
        return tournamentRepository.save(tournament);
    }

    @Override
    public Integer findNbVictoriesOfTeam(Team team) {
        return tournamentRepository.findNbVictoriesOfTeam(team);
    }

    @Override
    public List<Tournament> findAllTournament() {
        return tournamentRepository.findAll();
    }

    @Override
    public Optional<Tournament> findTournamentById(Long id) {
        return tournamentRepository.findById(id);
    }

}
