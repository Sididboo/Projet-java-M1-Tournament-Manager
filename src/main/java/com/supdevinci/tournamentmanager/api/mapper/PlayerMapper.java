package com.supdevinci.tournamentmanager.api.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.supdevinci.tournamentmanager.api.dto.PlayerDetailDto;
import com.supdevinci.tournamentmanager.api.dto.PlayerDto;
import com.supdevinci.tournamentmanager.model.Player;

/**
 * Player mapper.
 */
@Mapper(componentModel = "spring")
public interface PlayerMapper {

    List<PlayerDto> mapToListDto(List<Player> player);

    PlayerDetailDto mapToDto(Player player);

}