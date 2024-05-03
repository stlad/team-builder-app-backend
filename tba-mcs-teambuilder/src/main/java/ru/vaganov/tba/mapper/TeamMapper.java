package ru.vaganov.tba.mapper;

import org.mapstruct.Mapper;
import ru.vaganov.tba.model.Team;
import ru.vaganov.tba.model.dto.TeamDTO;

@Mapper(componentModel = "spring", uses = {ProjectMapper.class})
public interface TeamMapper {

    public TeamDTO toDTO(Team entity);
    public Team fromDTO(TeamDTO dto);
}
