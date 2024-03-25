package ru.vaganov.tba.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vaganov.tba.models.HardRole;

@Data @Builder
@AllArgsConstructor @NoArgsConstructor
public class RoleResultShortDTO {
    private Long id;
    private Long userId;
    private Long hardRoleId;
    private Integer position;
}
