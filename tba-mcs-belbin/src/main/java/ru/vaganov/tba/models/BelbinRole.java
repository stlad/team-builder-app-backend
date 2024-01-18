package ru.vaganov.tba.models;

import lombok.*;

@Data @Builder
@EqualsAndHashCode @NoArgsConstructor @AllArgsConstructor
public class BelbinRole {

    private Long id;
    private String rusName;
    private String engName;
    private String description;
    private RoleGroup group;


    public enum RoleGroup{
        INTELLIGENT,
        SOCIAL,
        ACTION
    }


}
