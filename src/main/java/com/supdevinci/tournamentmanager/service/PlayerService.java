package com.supdevinci.tournamentmanager.service;

import java.util.List;

import com.supdevinci.tournamentmanager.model.Player;

public interface PlayerService {
    Player savePlayer(Player player);
    List<Player> findAllPlayers();
}
