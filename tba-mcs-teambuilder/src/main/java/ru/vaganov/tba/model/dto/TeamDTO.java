package ru.vaganov.tba.model.dto;

import lombok.Data;
import ru.vaganov.tba.model.Project;

@Data
public class TeamDTO {
    private Long id;
    private String teamName;
    private ProjectDTO attachedProject;
}
