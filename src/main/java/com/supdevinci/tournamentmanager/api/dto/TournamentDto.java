package com.supdevinci.tournamentmanager.api.dto;


import lombok.Builder;
import lombok.Data;

import java.util.Date;



@Data
@Builder
public class TournamentDto {

    Long id;
    String subject;
    Date dateBegin;
    Integer numberOfParticipants;

}
