package com.supdevinci.tournamentmanager.api;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.supdevinci.tournamentmanager.api.dto.PlayerCreateDto;
import com.supdevinci.tournamentmanager.api.exception.IdMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.supdevinci.tournamentmanager.api.dto.PlayerDetailDto;
import com.supdevinci.tournamentmanager.api.dto.PlayerDto;
import com.supdevinci.tournamentmanager.api.exception.ResourceNotFoundException;
import com.supdevinci.tournamentmanager.api.mapper.PlayerMapper;
import com.supdevinci.tournamentmanager.model.Player;
import com.supdevinci.tournamentmanager.service.PlayerService;

import lombok.RequiredArgsConstructor;

import javax.validation.Valid;

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
     * Create players.
     */
    @PostMapping
    public ResponseEntity<PlayerDto> createPlayer(
            @RequestBody @Valid PlayerCreateDto playerCreateDto
    ){
        Player player = mapper.mapToEntity(playerCreateDto);
        Player createdPlayer = playerService.savePlayer(player);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.mapToDto(createdPlayer));
    }


    /**
     * Get all players.
     * 
     * @return the list of players
     */
    @GetMapping
    public ResponseEntity<List<PlayerDto>> getPlayers() {
        return ResponseEntity
                .ok(playerService
                        .findAllPlayers()
                        .stream()
                        .map((Player player) -> mapper.mapToDto(player))
                        .toList());
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
                .map(mapper::mapToDetailDto)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Player", id));
    }

    /**
     * Update player.
     *
     * @param id
     * @return update pseudo of one player
     */
    @PutMapping(path = "/{id}")
    public ResponseEntity<PlayerDto> updatePlayer(
            @PathVariable Long id,
            @RequestBody PlayerDto playerDto
    ) {
        Optional<Player> optionalPlayer = playerService.findPlayerById(id);
        if (optionalPlayer.isEmpty()) {
            throw new ResourceNotFoundException("Player", id);
        }

        if (!Objects.equals(id, playerDto.getId())) {
            throw new IdMismatchException(id, playerDto.getId());
        }

        Player player = optionalPlayer.get();
        player.setPseudo(playerDto.getPseudo());


        Player updatedPlayer = playerService.savePlayer(player);
        return ResponseEntity.ok(mapper.mapToDto(updatedPlayer));
    }






}
