package com.supdevinci.tournamentmanager.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Team entity
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String teamName;

    @ManyToMany
    private List<Player> players;

    @ManyToMany
    private List<Tournament> tournaments;

    /**
     * Contructor.
     * 
     * @param id
     * @param teamName
     * @param players
     */
    public Team(Long id, String teamName, List<Player> players) {
        this.id = id;
        this.teamName = teamName;
        this.players = players;
    }

}
