package com.supdevinci.tournamentmanager.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.supdevinci.tournamentmanager.model.State;
import com.supdevinci.tournamentmanager.repository.StateRepository;

import lombok.RequiredArgsConstructor;

/**
 * Class to initialize data in database.
 */
@Component
@RequiredArgsConstructor
public class DataLoader implements ApplicationRunner {

    private final StateRepository stateRepository;

    public void run(ApplicationArguments args) {
        // Insert states
        stateRepository.save(new State(1L, "Planifié"));
        stateRepository.save(new State(2L, "En Cours"));
        stateRepository.save(new State(3L, "Terminé"));
    }
}
