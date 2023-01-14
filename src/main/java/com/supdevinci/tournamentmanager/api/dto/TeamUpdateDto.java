package com.supdevinci.tournamentmanager.api.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Data;

/**
 * Update Team DTO.
 */
@Data
@Builder
public class TeamUpdateDto {

    Long id;
    @NotBlank
    String teamName;
    @NotEmpty
    List<Long> playerIds;

    // Mapping ignore if null value
    @JsonInclude(Include.NON_NULL)
    List<PlayerDto> players;

}