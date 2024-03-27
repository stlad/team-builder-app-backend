package ru.vaganov.tba.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
@AllArgsConstructor @NoArgsConstructor
public class HardRoleWithQuotaDTO {
    private HardRoleDTO role;
    private Integer quota;
    private Integer currentCount;
}
