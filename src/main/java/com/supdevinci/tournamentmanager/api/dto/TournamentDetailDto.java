package com.supdevinci.tournamentmanager.api.dto;

import java.util.Date;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TournamentDetailDto {

    Long id;
    String subject;
    String description;
    String nameState;
    Date dateBegin;
    List<TeamDto> teams;

}
