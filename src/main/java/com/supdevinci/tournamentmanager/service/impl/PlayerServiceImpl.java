package com.supdevinci.tournamentmanager.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.supdevinci.tournamentmanager.model.Player;
import com.supdevinci.tournamentmanager.repository.PlayerRepository;
import com.supdevinci.tournamentmanager.service.PlayerService;

import lombok.RequiredArgsConstructor;

/**
 * Service to interact with the database with the table player.
 */
@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    @Override
    public Player savePlayer(Player player) {
        return playerRepository.save(player);
    }

    @Override
    public List<Player> findAllPlayers() {
        return playerRepository.findAll();
    }

    @Override
    public Optional<Player> findPlayerById(Long id) {
        return playerRepository.findById(id);
    }


    @Override
    public String deletePlayer(Long id) {
        playerRepository.deleteById(id);
        return "Player n°"+id+" has been removed !";
    }


}
