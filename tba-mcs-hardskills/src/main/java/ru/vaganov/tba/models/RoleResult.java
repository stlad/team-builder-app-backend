package ru.vaganov.tba.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data @Builder
@AllArgsConstructor @NoArgsConstructor
@Entity @Table(name = "hard_role_results")
public class RoleResult {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private HardRole hardRole;

    private Long userId;
}
