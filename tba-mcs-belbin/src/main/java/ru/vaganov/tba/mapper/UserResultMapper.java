package ru.vaganov.tba.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.vaganov.tba.models.Question;
import ru.vaganov.tba.models.UserResult;
import ru.vaganov.tba.models.dto.QuestionDTO;
import ru.vaganov.tba.models.dto.UserResultDTO;
import ru.vaganov.tba.models.dto.UserResultShortDTO;

import java.util.List;

@Mapper(componentModel = "spring", uses = {BelbinRoleMapper.class})
public interface UserResultMapper {
    public abstract UserResult fromDto(UserResultDTO dto);
    public abstract  UserResultDTO toDto(UserResult entity);
    public abstract List<UserResultDTO> toDto(List<UserResult> entity);

    @Mapping(source = "entity.role.id", target = "roleId")
    public abstract  UserResultShortDTO toShortDto(UserResult entity);

    public abstract List<UserResultShortDTO> toShortDtos(List<UserResult> entity);
}
