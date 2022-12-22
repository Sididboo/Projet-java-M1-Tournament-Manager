package com.supdevinci.tournamentmanager.service;

import java.util.Optional;

import com.supdevinci.tournamentmanager.model.State;

/**
 * Interface for StateServiceImpl.
 */
public interface StateService {
    /**
     * Find one state by id.
     * 
     * @param id
     * @return optional one state
     */
    Optional<State> findStateById(Long id);
}
