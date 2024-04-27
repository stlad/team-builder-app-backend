package ru.vaganov.tba.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserExternalDTO {
    private Long id;
    private String username;

    private LocalDateTime dateRegister;
    private String firstname;
    private String lastname;
    private String middlename;
    private String academicGroup;
}
