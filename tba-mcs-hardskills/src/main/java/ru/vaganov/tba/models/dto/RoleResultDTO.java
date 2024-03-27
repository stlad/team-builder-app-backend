package ru.vaganov.tba.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vaganov.tba.models.HardRole;

@Data @Builder
@AllArgsConstructor @NoArgsConstructor
public class RoleResultDTO {
    private Long id;
    private Long userId;
    private HardRole hardRole;
}
