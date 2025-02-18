package mk.ukim.finki.wp.kol2022.g1.model;


import lombok.Data;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate employmentDate;
    private String name;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private EmployeeType type;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Skill> skills;

    public Employee() {
    }

    public Employee(String name, String email, String password, EmployeeType type, List<Skill> skills, LocalDate employmentDate) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.type = type;
        this.skills = skills;
        this.employmentDate = employmentDate;
    }

}
