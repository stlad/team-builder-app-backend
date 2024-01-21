package ru.vaganov.tba.models.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class BlockQuestionDTO {
    private Long id;
    private String blockContent;
    private Long number;
}
