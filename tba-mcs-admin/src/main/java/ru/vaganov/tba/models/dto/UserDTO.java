package ru.vaganov.tba.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vaganov.tba.models.SystemRoles;

import java.time.LocalDateTime;

@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class UserDTO {

    private Long id;
    private String username;

    private LocalDateTime dateRegister;
    private String firstname;
    private String lastname;
    private String middlename;
    private String email;

    private String password;

    private SystemRoles role;
}
