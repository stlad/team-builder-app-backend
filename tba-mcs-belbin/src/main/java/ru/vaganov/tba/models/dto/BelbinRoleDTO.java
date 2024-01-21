package ru.vaganov.tba.models.dto;

import lombok.*;
import ru.vaganov.tba.models.BelbinRole;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class BelbinRoleDTO {
    private Long id;
    private String rusName;
    private String engName;
    private String description;
    private BelbinRole.RoleGroup group;
    private Long number;
}
