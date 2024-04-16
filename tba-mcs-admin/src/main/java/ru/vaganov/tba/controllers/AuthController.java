package ru.vaganov.tba.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vaganov.tba.models.dto.UserDTO;
import ru.vaganov.tba.service.AuthService;
import ru.vaganov.tba.service.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
@Tag(name = "Auth API")
@Slf4j
public class AuthController {

    @Autowired
    private AuthService authService;


    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody UserDTO dto){
        return new ResponseEntity<>(authService.login(dto), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody UserDTO dto){
        return new ResponseEntity<>(authService.register(dto), HttpStatus.OK);
    }
}
