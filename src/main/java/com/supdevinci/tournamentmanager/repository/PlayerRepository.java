package com.supdevinci.tournamentmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.supdevinci.tournamentmanager.model.Player;

/**
 * Player repository.
 */
public interface PlayerRepository extends JpaRepository<Player, Long> {
    List<Player> findByIdIn(List<Long> ids);
}
