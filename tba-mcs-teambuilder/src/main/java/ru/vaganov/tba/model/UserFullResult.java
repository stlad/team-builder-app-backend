package ru.vaganov.tba.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data @Entity
@Table(name = "user_results")
public class UserFullResult {

    @Id
    private Long id;
    private Long userId;
    private Long teamRoleId;
    private Long profRoleId;
    private Long projectId;
}
