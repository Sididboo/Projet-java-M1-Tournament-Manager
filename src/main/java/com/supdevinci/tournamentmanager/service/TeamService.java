package com.supdevinci.tournamentmanager.service;

import com.supdevinci.tournamentmanager.model.Team;

/**
 * Interface for TeamServiceImpl.
 */
public interface TeamService {
    /**
     * Save player in database.
     * 
     * @param team
     * @return the player saved
     */
    Team saveTeam(Team team);
}
