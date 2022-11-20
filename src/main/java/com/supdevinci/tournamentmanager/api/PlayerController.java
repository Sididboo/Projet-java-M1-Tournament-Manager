package com.supdevinci.tournamentmanager.api;

import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.supdevinci.tournamentmanager.api.dto.PlayerDto;
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
    private PlayerMapper mapper = Mappers.getMapper(PlayerMapper.class);

    /**
     * Get all players.
     * 
     * @return the list of players
     */
    @GetMapping
    public ResponseEntity<List<PlayerDto>> getPlayers() {
        return ResponseEntity.ok(mapper.mapToDto(playerService.findAllPlayers()));
    }

}
