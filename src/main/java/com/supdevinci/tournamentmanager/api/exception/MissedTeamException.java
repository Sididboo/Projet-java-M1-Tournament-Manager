package com.supdevinci.tournamentmanager.api.exception;

/**
 * Bad request exception custom.
 * When updating the tournament status and the user has forgotten the team when
 * the choice is "Terminé". (stateId = 3).
 */
public class MissedTeamException extends RuntimeException {
    public MissedTeamException(Long stateId) {
        super(String.format("The request body's winningTeamId is required when stateId is %s (\"Terminé\").", stateId));
    }
}
