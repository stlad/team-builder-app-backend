package ru.vaganov.tba.mapper;

import org.mapstruct.Mapper;
import ru.vaganov.tba.models.Question;
import ru.vaganov.tba.models.UserResult;
import ru.vaganov.tba.models.dto.QuestionDTO;
import ru.vaganov.tba.models.dto.UserResultDTO;

import java.util.List;

@Mapper(componentModel = "spring", uses = {BelbinRoleMapper.class})
public interface UserResultMapper {
    public abstract UserResult fromDto(UserResultDTO dto);
    public abstract  UserResultDTO toDto(UserResult entity);
    public abstract List<UserResultDTO> toDto(List<UserResult> entity);
}
