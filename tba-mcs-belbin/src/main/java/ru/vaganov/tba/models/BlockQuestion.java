package ru.vaganov.tba.models;


import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "blocks")
@Data @Builder
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode
public class BlockQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String blockContent;

    private Long number;
}
