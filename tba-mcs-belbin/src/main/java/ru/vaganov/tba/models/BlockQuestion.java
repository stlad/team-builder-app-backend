package ru.vaganov.tba.models;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Data @Builder
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode
public class BlockQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String blockContent;
}
