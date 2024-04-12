package ru.vaganov.tba.model.dto;

import lombok.Data;

@Data
public class UserResultDTO {
    private Long userId;
    private Long teamRoleId;
    private Long profRoleId;
}
