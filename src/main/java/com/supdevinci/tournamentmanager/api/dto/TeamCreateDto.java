package com.supdevinci.tournamentmanager.api.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TeamCreateDto {

    @NotEmpty(message = "Team name may not be empty")
    @NotNull(message = "Team name may not be null")
    public String teamName;

    public List<Long> playerIds;

}
