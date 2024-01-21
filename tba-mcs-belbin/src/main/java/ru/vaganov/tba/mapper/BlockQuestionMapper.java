package ru.vaganov.tba.mapper;

import org.mapstruct.Mapper;
import ru.vaganov.tba.models.BlockQuestion;
import ru.vaganov.tba.models.dto.BlockQuestionDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BlockQuestionMapper {
    public abstract BlockQuestion fromDto(BlockQuestionDTO dto);
    public abstract  BlockQuestionDTO toDto(BlockQuestion entity);
    public abstract List<BlockQuestionDTO> toDto(List<BlockQuestion> entity);
}
