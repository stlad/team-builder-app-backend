package ru.vaganov.tba.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data @Builder
@NoArgsConstructor @AllArgsConstructor
@Entity @Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private LocalDateTime dateRegister;
    private String firstname;
    private String lastname;
    private String middlename;

    @Column(unique=true)
    private String email;

    private String password;

    @Column(length = 32, columnDefinition = "varchar(32) default 'STUDENT'")
    @Enumerated(value = EnumType.STRING)
    private SystemRoles systemRole;
}
