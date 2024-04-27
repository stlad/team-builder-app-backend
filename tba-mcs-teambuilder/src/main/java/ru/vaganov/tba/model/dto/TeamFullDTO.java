package ru.vaganov.tba.model.dto;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import ru.vaganov.tba.dto.UserExternalDTO;
import ru.vaganov.tba.model.Project;

import java.util.List;

@Data
public class TeamFullDTO {
    private Long id;
    private String teamName;
    private Project attachedProject;

    List<UserFullDTO> members;
}
