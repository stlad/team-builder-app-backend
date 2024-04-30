package ru.vaganov.tba.mapper;

import org.mapstruct.Mapper;
import ru.vaganov.tba.model.Project;
import ru.vaganov.tba.model.dto.ProjectDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    ProjectDTO toDTO(Project entity);
    Project fromDTO(ProjectDTO dto);
    List<ProjectDTO> toDTO(List<Project> entities);
}
