package ru.vaganov.tba.models;

import lombok.*;


@Data @Builder
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode
public class Question {

    private Long id;
    private String content;
    private BelbinRole attachedRole;
    private BlockQuestion attachedBlock;
}
