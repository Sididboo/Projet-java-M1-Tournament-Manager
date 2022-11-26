package com.supdevinci.tournamentmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.supdevinci.tournamentmanager.model.Team;

/**
 * Team repository.
 */
public interface TeamRepository extends JpaRepository<Team, Long> {

}
