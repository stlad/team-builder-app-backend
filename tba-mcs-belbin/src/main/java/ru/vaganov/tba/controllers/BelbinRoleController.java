package ru.vaganov.tba.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vaganov.tba.models.BelbinRole;
import ru.vaganov.tba.models.dto.BelbinRoleDTO;
import ru.vaganov.tba.service.BelbinService;

import java.time.LocalDateTime;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/roles")
@Tag(name = "Roles API")
@Slf4j
public class BelbinRoleController {

    @Autowired
    private BelbinService belbinService;

    @Operation(summary = "Получение роли Белбина по имени", description = "Получение роли Белбина по имени")
    @GetMapping("/{roleName}")
    public ResponseEntity<BelbinRoleDTO> getRoleByName(@PathVariable(value = "roleName") String roleName){
        BelbinRoleDTO dto = belbinService.getBelbinRoleByName(roleName);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Получение роли Белбина по ID", description = "Получение роли Белбина по идентификатору")
    @GetMapping("/id/{id}")
    public ResponseEntity<BelbinRoleDTO> getRoleById(@PathVariable(value = "id") Long id){
        return new ResponseEntity<>(belbinService.getRoleById(id), HttpStatus.OK);
    }
}
