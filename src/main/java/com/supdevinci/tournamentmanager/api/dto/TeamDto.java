package com.supdevinci.tournamentmanager.api.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Data;

/**
 * Team DTO.
 */
@Data
@Builder
public class TeamDto {

    Long id;
    String teamName;
    // Mapping ignore if null value
    @JsonInclude(Include.NON_NULL)
    List<PlayerDto> players;

}
