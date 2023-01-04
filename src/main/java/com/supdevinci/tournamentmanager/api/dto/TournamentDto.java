package com.supdevinci.tournamentmanager.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TournamentDto {

    Long id;
    String subject;

    // Mapping ignore if null value
    @JsonInclude(JsonInclude.Include.NON_NULL)
    List<TeamDto> teams;

}
