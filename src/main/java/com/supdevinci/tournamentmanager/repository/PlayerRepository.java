package com.supdevinci.tournamentmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.supdevinci.tournamentmanager.model.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {

}
