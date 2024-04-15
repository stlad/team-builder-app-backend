package ru.vaganov.tba.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vaganov.tba.models.dto.HardRoleDTO;
import ru.vaganov.tba.service.HardRolesService;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/roles")
@Tag(name = "Hard Roles API")
@Slf4j
public class HardRolesController {

    @Autowired
    private HardRolesService hardRolesService;


    @Operation(summary = "Получение всех проф. ролей", description = "Получение всего каталога проф. ролей")
    @GetMapping("/all")
    public ResponseEntity<List<HardRoleDTO>> findAllRoles(){
        return new ResponseEntity<>(hardRolesService.findAllRoles(), HttpStatus.OK);
    }

    @Operation(summary = "Получение проф. роли по её ID", description = "Получение проф. роли по её ID")
    @GetMapping("/id/{id}")
    public ResponseEntity<HardRoleDTO> findRoleById(@PathVariable Long id){
        return new ResponseEntity<>(hardRolesService.findRoleById(id), HttpStatus.OK);
    }

    @Operation(summary = "Получение проф. роли по её имени", description = "Получение проф. роли по её имени")
    @GetMapping("/{roleName}")
    public ResponseEntity<HardRoleDTO> findRoleByName(@PathVariable String roleName){
        return new ResponseEntity<>(hardRolesService.findRoleByName(roleName), HttpStatus.OK);
    }
}
