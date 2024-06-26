package ru.vaganov.tba.model.dto;

import lombok.Data;

@Data
public class ProjectDTO {
    private Long id;
    private String name;
    private String description;
    private String customer;

    private Integer teamsCount;
}
