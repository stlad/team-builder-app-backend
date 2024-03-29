package ru.vaganov.tba.controllers.handlers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vaganov.tba.models.dto.UserDTO;
import ru.vaganov.tba.service.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/users")
@Tag(name = "Users API")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findUserById(@PathVariable(value = "id") Long id){
        UserDTO dto = userService.findUserById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }


    @PostMapping("/")
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO dto){
        dto = userService.saveUser(dto);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
