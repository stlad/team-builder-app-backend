package ru.vaganov.tba.model.dto;

import lombok.Data;
import ru.vaganov.tba.dto.BelbinRoleExternalDTO;
import ru.vaganov.tba.dto.HardRoleExternalDTO;
import ru.vaganov.tba.dto.UserExternalDTO;

import java.time.LocalDateTime;

@Data
public class UserFullDTO {

    private Long id;
    private String username;
    private LocalDateTime dateRegister;
    private String firstname;
    private String lastname;
    private String middlename;
    private String academicGroup;
    private Long teamId;

    private BelbinRoleExternalDTO teamRole;
    private HardRoleExternalDTO profRole;
}
