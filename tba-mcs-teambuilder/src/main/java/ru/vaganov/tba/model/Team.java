package ru.vaganov.tba.model;

import jakarta.persistence.*;
import lombok.Data;
import ru.vaganov.tba.model.Project;

@Data @Entity
@Table(name = "teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String teamName;

    @ManyToOne
    @JoinColumn(name = "project_id",referencedColumnName = "id")
    private Project attachedProject;
}
