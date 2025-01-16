package mk.ukim.finki.wp.kol2023.g1.model;

import lombok.Data;

import jakarta.persistence.*;


@Data
@Entity
public class Team {

    public Team() {
    }

    public Team(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

}
