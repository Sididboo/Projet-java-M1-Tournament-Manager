package com.supdevinci.tournamentmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.supdevinci.tournamentmanager.model.Team;
import com.supdevinci.tournamentmanager.model.Tournament;

/**
 * Tournament repository.
 */
public interface TournamentRepository extends JpaRepository<Tournament, Long> {

    @Query(value = "SELECT COUNT(t) FROM Tournament t WHERE t.winningTeam = ?1")
    Integer findNbVictoriesOfTeam(Team team);

}