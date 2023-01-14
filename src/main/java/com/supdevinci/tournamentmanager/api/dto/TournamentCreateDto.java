package com.supdevinci.tournamentmanager.api.dto;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TournamentCreateDto {

    @NotBlank
    String subject;
    @NotBlank
    String description;
    @NotNull
    Date dateBegin;
    @NotEmpty
    List<Long> teamIds;

}
