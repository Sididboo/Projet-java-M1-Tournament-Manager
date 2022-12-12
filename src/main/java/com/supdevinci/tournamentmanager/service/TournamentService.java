package com.supdevinci.tournamentmanager.service;

import com.supdevinci.tournamentmanager.model.Team;

/**
 * Interface for TournamentServiceImpl.
 */
public interface TournamentService {

    /**
     * Find number victories of team.
     * 
     * @param team
     * @return number of victories
     */
    Integer findNbVictoriesOfTeam(Team team);

}
