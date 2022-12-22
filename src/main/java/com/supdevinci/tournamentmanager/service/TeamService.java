package com.supdevinci.tournamentmanager.service;

import java.util.List;
import java.util.Optional;

import com.supdevinci.tournamentmanager.model.Team;

/**
 * Interface for TeamServiceImpl.
 */
public interface TeamService {
    /**
     * Save team in database.
     * 
     * @param team
     * @return the team saved
     */
    Team saveTeam(Team team);

    /**
     * Find all teams.
     * 
     * @return the list of all teams
     */
    List<Team> findAllTeams();

    /**
     * Find one team by id.
     * 
     * @param id
     * @return one team
     */
    Optional<Team> findTeamById(Long id);

    /**
     * 
     * Find teams with list of identifiers.
     * 
     * @param ids
     * @return list of teams
     */
    List<Team> findTeamsByIds(List<Long> ids);
}
