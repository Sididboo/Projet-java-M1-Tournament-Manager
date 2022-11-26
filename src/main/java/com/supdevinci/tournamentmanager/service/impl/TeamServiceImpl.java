package com.supdevinci.tournamentmanager.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.supdevinci.tournamentmanager.model.Team;
import com.supdevinci.tournamentmanager.repository.TeamRepository;
import com.supdevinci.tournamentmanager.service.TeamService;

import lombok.RequiredArgsConstructor;

/**
 * Service to interact with the database with the table team.
 */
@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;

    @Override
    public Team saveTeam(Team team) {
        return teamRepository.save(team);
    }

    @Override
    public List<Team> findAllTeams() {
        return teamRepository.findAll();
    }

}
