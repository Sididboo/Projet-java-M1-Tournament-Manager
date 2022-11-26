package com.supdevinci.tournamentmanager.constant;

import java.util.Arrays;

import com.supdevinci.tournamentmanager.model.Player;
import com.supdevinci.tournamentmanager.model.Team;

/**
 * Test data
 */
public class Constant {
    // Player
    public static Player P1 = new Player(1L, "P1", "24000");
    public static Player P2 = new Player(2L, "P2", "24001");
    public static Player P3 = new Player(3L, "P3", "24002");
    // Team
    public static Team T1 = new Team(1L, "T1", Arrays.asList(P1));
    public static Team T2 = new Team(2L, "T2", Arrays.asList(P2, P3));
}
