package com.supdevinci.tournamentmanager.api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlayerDto {
    
    Long id;
    String speudo;

}
