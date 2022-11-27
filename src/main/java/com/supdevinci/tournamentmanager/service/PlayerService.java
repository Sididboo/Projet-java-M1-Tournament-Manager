package com.supdevinci.tournamentmanager.service;

import java.util.List;
import java.util.Optional;

import com.supdevinci.tournamentmanager.model.Player;

/**
 * Interface for PlayerServiceImpl.
 */
public interface PlayerService {

    /**
     * Create player in database.
     * or
     * Update player in database
     *
     * @param player
     * @return the player saved
     */
    Player savePlayer(Player player);

    /**
     * Find all players.
     * 
     * @return the list of all players
     */
    List<Player> findAllPlayers();

    /**
     * Find one player by id.
     * 
     * @param id
     * @return one player
     */
    Optional<Player> findPlayerById(Long id);


    /**
     * Delete one player by id.
     *
     * @param id
     * @return one player
     */
    String deletePlayer(Long id);

}
