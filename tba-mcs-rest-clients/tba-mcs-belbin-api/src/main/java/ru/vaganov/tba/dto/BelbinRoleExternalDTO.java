package ru.vaganov.tba.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class BelbinRoleExternalDTO {
    private Long id;
    private String rusName;
    private String engName;
    private String description;
    private String group;
    private Long number;
}
