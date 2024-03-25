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

    private Long userId;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "first_role_id", referencedColumnName = "id")
    private HardRole first;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "second_role_id", referencedColumnName = "id")
    private HardRole second;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "third_role_id", referencedColumnName = "id")
    private HardRole third;
}
