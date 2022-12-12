package com.supdevinci.tournamentmanager.api.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TeamCreateDto {

    @NotBlank
    String teamName;

    @NotEmpty
    List<Long> playerIds;

}
