package mk.ukim.finki.wp.kol2023.g1.model;

import lombok.Data;

import jakarta.persistence.*;

@Data
@Entity
public class Player {

    public Player() {
    }

    public Player(String name, String bio, Double pointsPerGame, PlayerPosition position, Team team) {
        this.name = name;
        this.bio = bio;
        this.pointsPerGame = pointsPerGame;
        this.position = position;
        this.team = team;
        this.votes = 0;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String bio;

    private Double pointsPerGame;

    @Enumerated(value = EnumType.STRING)
    private PlayerPosition position;

    @ManyToOne
    private Team team;

    private Integer votes = 0;
}
