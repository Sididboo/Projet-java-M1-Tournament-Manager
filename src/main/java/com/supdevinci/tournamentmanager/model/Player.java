package com.supdevinci.tournamentmanager.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Player entity
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String speudo;

    @Column(nullable = false)
    private String postalAdress;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Team> teams;

    /**
     * Constructor.
     * 
     * @param id
     * @param speudo
     * @param postalAdress
     */
    public Player(Long id, String speudo, String postalAdress) {
        this.id = id;
        this.speudo = speudo;
        this.postalAdress = postalAdress;
    }

}
