package com.supdevinci.tournamentmanager.constant;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.supdevinci.tournamentmanager.model.Player;
import com.supdevinci.tournamentmanager.model.State;
import com.supdevinci.tournamentmanager.model.Team;
import com.supdevinci.tournamentmanager.model.Tournament;

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
    // State
    public static State S1 = new State(1L, "Planifié");
    public static State S2 = new State(2L, "En Cours");
    public static State S3 = new State(3L, "Terminé");
    // Tournament
    public static Tournament TO1 = new Tournament(
            1L,
            "TO1",
            "TO1 Desc",
            new Timestamp(new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime().getTime()),
            S1,
            Arrays.asList(T1, T2));
    public static Tournament TO2 = new Tournament(
            2L,
            "TO2",
            "TO2 Desc",
            new Timestamp(new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime().getTime()),
            S2,
            Arrays.asList(T1, T2));
    public static Tournament TO3 = new Tournament(
            3L,
            "TO3",
            "TO3 Desc",
            new Timestamp(new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime().getTime()),
            S3,
            Arrays.asList(T1, T2), T1);
}
