package com.supdevinci.tournamentmanager.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Update Team DTO.
 */
@Data
@Builder
public class TeamUpdateDto {

    public Long id;

    @NotEmpty(message = "Team name may not be empty")
    @NotNull(message = "Team name may not be null")
    public String teamName;

    // Mapping ignore if null value
    @JsonInclude(Include.NON_NULL)
    public List<PlayerDto> players;
    public List<Long> playerIds;

}