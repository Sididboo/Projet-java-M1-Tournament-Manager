package com.supdevinci.tournamentmanager.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Tournament entity
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String subject;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Timestamp dateBegin;

    @ManyToOne(fetch = FetchType.LAZY)
    private State state;

    @ManyToMany
    private List<Team> teams;

    @Column(nullable = false)
    private Integer numberOfParticipants;

    @OneToOne
    private Team winningTeam;

    /**
     * Constructor.
     * 
     * @param id
     * @param subject
     * @param description
     * @param dateBegin
     * @param state
     * @param teams
     * @param numberOfParticipants
     */
    public Tournament(Long id, String subject, String description, Timestamp dateBegin, State state, List<Team> teams, Integer numberOfParticipants) {
        this.id = id;
        this.subject = subject;
        this.description = description;
        this.dateBegin = dateBegin;
        this.state = state;
        this.teams = teams;
        this.numberOfParticipants = numberOfParticipants;

    }


}
