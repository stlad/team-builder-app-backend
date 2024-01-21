package ru.vaganov.tba.models.dto;

import lombok.*;
import ru.vaganov.tba.models.BelbinRole;
import ru.vaganov.tba.models.BlockQuestion;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class QuestionDTO {    private Long id;
    private String content;
    private Long number;
    private BelbinRoleDTO attachedRole;
    private BlockQuestionDTO attachedBlock;
}
