package ru.vaganov.tba.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
@AllArgsConstructor @NoArgsConstructor
@Entity  @Table(name = "hard_roles")
public class HardRole {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String engName;
    private String rusName;
    private String description;

    @Column(name = "industry")
    @Enumerated(value = EnumType.STRING)
    private HardRole.Industry industry;

    public enum Industry {
        IT,
        RADIO_ENGINEERING
    }
}
