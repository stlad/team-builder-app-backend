package ru.vaganov.tba.mapper;

import org.mapstruct.Mapper;
import ru.vaganov.tba.model.Project;
import ru.vaganov.tba.model.dto.ProjectDTO;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    ProjectDTO toDTO(Project entity);
    ProjectDTO fromDTO(ProjectDTO dto);
}
