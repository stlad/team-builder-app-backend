package ru.vaganov.tba.model;

import jakarta.persistence.*;
import lombok.Data;

@Data @Entity
@Table(name = "user_results")
public class UserFullResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long teamRoleId;
    private Long profRoleId;

    @ManyToOne
    @JoinColumn(name = "team_id", referencedColumnName = "id")
    private Team team;
}
