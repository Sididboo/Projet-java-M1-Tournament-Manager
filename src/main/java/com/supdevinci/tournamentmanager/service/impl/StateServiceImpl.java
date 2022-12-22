package com.supdevinci.tournamentmanager.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.supdevinci.tournamentmanager.model.State;
import com.supdevinci.tournamentmanager.repository.StateRepository;
import com.supdevinci.tournamentmanager.service.StateService;

import lombok.RequiredArgsConstructor;

/**
 * Service to interact with the database with the table state.
 */
@Service
@RequiredArgsConstructor
public class StateServiceImpl implements StateService {

    private final StateRepository stateRepository;

    @Override
    public Optional<State> findStateById(Long id) {
        return stateRepository.findById(id);
    }

}
