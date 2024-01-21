package ru.vaganov.tba.mapper;

import org.mapstruct.Mapper;
import ru.vaganov.tba.models.Question;
import ru.vaganov.tba.models.dto.QuestionDTO;

import java.util.List;

@Mapper(componentModel = "spring", uses = {BlockQuestionMapper.class, BelbinRoleMapper.class})
public interface QuestionMapper {
    public abstract Question fromDto(QuestionDTO dto);
    public abstract  QuestionDTO toDto(Question entity);
    public abstract List<QuestionDTO> toDto(List<Question> entity);
}
