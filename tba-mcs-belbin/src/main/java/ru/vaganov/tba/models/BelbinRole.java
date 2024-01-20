package ru.vaganov.tba.models;

import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "role_catalog")
@Data @Builder
@EqualsAndHashCode @NoArgsConstructor @AllArgsConstructor
public class BelbinRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String rusName;
    private String engName;
    private String description;

    @Column(name = "role_group")
    @Enumerated(value = EnumType.STRING)
    private RoleGroup group;
    private Long number;


    public enum RoleGroup{
        INTELLIGENT,
        SOCIAL,
        ACTION
    }
}
