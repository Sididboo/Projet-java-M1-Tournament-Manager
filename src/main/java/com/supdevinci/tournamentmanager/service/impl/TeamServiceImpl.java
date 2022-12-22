package com.supdevinci.tournamentmanager.service.impl;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<Team> findTeamById(Long id) {
        return teamRepository.findById(id);
    }

    @Override
    public List<Team> findTeamsByIds(List<Long> ids) {
        return teamRepository.findByIdIn(ids);
    }

}
