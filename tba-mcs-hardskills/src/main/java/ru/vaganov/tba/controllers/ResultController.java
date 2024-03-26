package ru.vaganov.tba.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vaganov.tba.AdminApiClient;
import ru.vaganov.tba.dto.UserExternalDTO;
import ru.vaganov.tba.models.dto.RoleResultDTO;
import ru.vaganov.tba.models.dto.RoleResultShortDTO;
import ru.vaganov.tba.service.HardRolesService;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/results")
@Tag(name = "Hard Roles Results API")
@Slf4j
public class ResultController {

    @Autowired
    private AdminApiClient adminApiClient;

    @Autowired
    private HardRolesService hardRolesService;

    @PostMapping("/{userId}")
    public ResponseEntity<List<RoleResultDTO>> addResult(
            @PathVariable Long userId, @RequestBody List<RoleResultShortDTO> dtos){
        List<RoleResultDTO> resultDTOS = hardRolesService.addResults(dtos, userId);
        return new ResponseEntity<>(resultDTOS, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserExternalDTO> getUserFromApi(@PathVariable Long id){
        UserExternalDTO userExternalDTO = adminApiClient.getUserById(id);
        log.info(userExternalDTO.toString());
        return new ResponseEntity<>(userExternalDTO, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<RoleResultDTO>> getUsersRoles(@PathVariable Long id){
        List<RoleResultDTO> resultDTOS = hardRolesService.findAllResultsByUser(id);
        return new ResponseEntity<>(resultDTOS, HttpStatus.OK);
    }
}
