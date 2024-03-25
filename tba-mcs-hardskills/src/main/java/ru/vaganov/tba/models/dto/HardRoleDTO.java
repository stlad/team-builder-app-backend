package ru.vaganov.tba.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vaganov.tba.models.HardRole;

@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class HardRoleDTO {

    private Long id;
    private String engName;
    private String rusName;
    private String description;
    private HardRole.Industry industry;

}
