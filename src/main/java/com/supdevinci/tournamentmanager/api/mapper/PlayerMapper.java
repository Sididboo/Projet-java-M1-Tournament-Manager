package com.supdevinci.tournamentmanager.api.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.supdevinci.tournamentmanager.api.dto.PlayerDto;
import com.supdevinci.tournamentmanager.model.Player;

@Mapper(componentModel = "spring")
public interface PlayerMapper {

    List<PlayerDto> mapToDto(List<Player> player);

}