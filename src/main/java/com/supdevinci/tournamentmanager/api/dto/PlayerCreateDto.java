package com.supdevinci.tournamentmanager.api.dto;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlayerCreateDto {
    @NotNull
    private String pseudo;
    private String postalAddress;

}
