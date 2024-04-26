package ru.vaganov.tba.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vaganov.tba.models.dto.UserDTO;
import ru.vaganov.tba.service.UserService;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/users")
@Tag(name = "Users API")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @Operation(summary = "Поиск пользователя по идентификатору", description = "Поиск пользователя по ID")
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findUserById(@PathVariable(value = "id") Long id){
        UserDTO dto = userService.findUserById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }


    @Operation(summary = "Сохранение нового пользователя", description = "Сохранение нового пользователя")
    @PostMapping("/")
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO dto){
        dto = userService.saveUser(dto);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Изменение существующего пользователя", description = "Изменение пользователя")
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> editUser(@PathVariable Long id, @RequestBody UserDTO dto){
        return new ResponseEntity<>(userService.updateUser(id, dto), HttpStatus.OK);
    }

    @Operation(summary = "Поиск списка пользователей по их id", description = "Поиск списка пользователей по их id")
    @PostMapping("/ids")
    public ResponseEntity<List<UserDTO>> editUser(@RequestBody List<Long> ids){
        return new ResponseEntity<>(userService.findUsersByIds(ids), HttpStatus.OK);
    }
}
