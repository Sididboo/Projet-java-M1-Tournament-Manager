package com.supdevinci.tournamentmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.supdevinci.tournamentmanager.model.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {

}
