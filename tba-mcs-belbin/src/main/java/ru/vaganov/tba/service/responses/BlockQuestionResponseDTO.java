package ru.vaganov.tba.service.responses;

import lombok.*;
import ru.vaganov.tba.models.dto.BlockQuestionDTO;
import ru.vaganov.tba.models.dto.QuestionDTO;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class BlockQuestionResponseDTO {
   private BlockQuestionDTO blockQuestion;
   private List<QuestionDTO> questions;
}
