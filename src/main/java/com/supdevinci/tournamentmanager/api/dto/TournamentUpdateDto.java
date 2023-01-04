package com.supdevinci.tournamentmanager.api.dto;

import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TournamentUpdateDto {

    @NotNull
    Long id;
    @NotNull
    Long stateId;

    Long winningTeamId;
}
