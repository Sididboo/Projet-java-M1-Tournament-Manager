package com.supdevinci.tournamentmanager.api;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.supdevinci.tournamentmanager.api.dto.PlayerDetailDto;
import com.supdevinci.tournamentmanager.api.dto.PlayerDto;
import com.supdevinci.tournamentmanager.api.exception.ResourceNotFoundException;
import com.supdevinci.tournamentmanager.api.mapper.PlayerMapper;
import com.supdevinci.tournamentmanager.service.PlayerService;

import lombok.RequiredArgsConstructor;

/**
 * Player controller.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/v1/player", produces = MediaType.APPLICATION_JSON_VALUE)
public class PlayerController {

    private final PlayerService playerService;
    private final PlayerMapper mapper;

    /**
     * Get all players.
     * 
     * @return the list of players
     */
    @GetMapping
    public ResponseEntity<List<PlayerDto>> getPlayers() {
        return ResponseEntity.ok(mapper.mapToListDto(playerService.findAllPlayers()));
    }

    /**
     * Get player details.
     * 
     * @param id
     * @return the details of one player
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<PlayerDetailDto> getPlayerById(@PathVariable(name = "id") Long id) {
        return playerService.findPlayerById(id)
                .map(mapper::mapToDto)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Player", id));
    }

}
