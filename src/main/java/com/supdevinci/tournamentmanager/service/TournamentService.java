package com.supdevinci.tournamentmanager.service;

import java.util.List;
import java.util.Optional;

import com.supdevinci.tournamentmanager.model.Team;
import com.supdevinci.tournamentmanager.model.Tournament;

/**
 * Interface for TournamentServiceImpl.
 */
public interface TournamentService {

    /**
     * Save tournament in database.
     * 
     * @param tournament
     * @return the tournament saved
     */
    Tournament saveTournament(Tournament tournament);

    /**
     * Find number victories of team.
     * 
     * @param team
     * @return number of victories
     */
    Integer findNbVictoriesOfTeam(Team team);

    /**
     * Find all tournament.
     *
     * @return all tournament.
     */
    List<Tournament> findAllTournament();

    /**
     * Find one tournament by id.
     * 
     * @param id
     * @return one tournament
     */
    Optional<Tournament> findTournamentById(Long id);

}
