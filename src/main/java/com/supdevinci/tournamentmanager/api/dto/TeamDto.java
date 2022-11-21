package com.supdevinci.tournamentmanager.api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TeamDto {
    
    Long id;
    String teamName;

}
