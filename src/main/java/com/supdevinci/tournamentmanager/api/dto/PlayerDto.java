package com.supdevinci.tournamentmanager.api.dto;

import lombok.Builder;
import lombok.Data;

/**
 * Player DTO.
 */
@Data
@Builder
public class PlayerDto {
    
    Long id;
    String pseudo;

}
