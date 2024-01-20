package ru.vaganov.tba.models;

import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "questions")
@Data @Builder
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private Long number;

    @ManyToOne
    @JoinColumn(name = "attached_role_id", referencedColumnName = "id")
    private BelbinRole attachedRole;

    @ManyToOne
    @JoinColumn(name = "attached_block_id", referencedColumnName = "id")
    private BlockQuestion attachedBlock;
}
