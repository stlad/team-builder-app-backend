package ru.vaganov.tba.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class UserResultDTO {
    private Long id;
    private Long userId;
    private BelbinRoleDTO role;
}
