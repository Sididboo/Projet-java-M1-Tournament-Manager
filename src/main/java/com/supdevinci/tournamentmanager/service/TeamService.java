package com.supdevinci.tournamentmanager.service;

import java.util.List;

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

    /**
     * Find all teams.
     * 
     * @return the list of all teams
     */
    List<Team> findAllTeams();
}
