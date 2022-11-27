package com.supdevinci.tournamentmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.supdevinci.tournamentmanager.model.State;

/**
 * State repository.
 */
public interface StateRepository extends JpaRepository<State, Long> {

}
