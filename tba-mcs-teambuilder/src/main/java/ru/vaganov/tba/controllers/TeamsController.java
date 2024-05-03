package ru.vaganov.tba.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vaganov.tba.model.UserFullResult;
import ru.vaganov.tba.model.dto.TeamDTO;
import ru.vaganov.tba.model.dto.TeamFullDTO;
import ru.vaganov.tba.model.dto.UserFullDTO;
import ru.vaganov.tba.service.TeambuildingService;
import ru.vaganov.tba.service.TeamsService;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/teams")
@Tag(name = "Teams API")
@Slf4j
public class TeamsController {

    @Autowired
    private TeambuildingService teambuildingService;
    @Autowired
    private TeamsService teamsService;


    @Operation(description = "Получить список членов команды", summary = "Получить список членов команды")
    @GetMapping("/{teamId}")
    public ResponseEntity<List<UserFullResult>> getMembersOfTeam(@PathVariable Long teamId){
        return new ResponseEntity<>(teamsService.getMembersByTeamId(teamId), HttpStatus.OK);
    }

    @Operation(description = "Получить всю информацию о команде", summary = "Получить всю информацию о команде")
    @GetMapping("/{teamId}/full")
    public ResponseEntity<TeamFullDTO> getFullTeams(@PathVariable Long teamId){
        return new ResponseEntity<>(teamsService.getTeamFullDTO(teamId), HttpStatus.OK);
    }

    @Operation(description = "Получить всех пользователей с информацией о ролях", summary = "Получить всех пользователей с информацией о ролях")
    @GetMapping("/users/all")
    public ResponseEntity<List<UserFullDTO>> getAllUsersWithRoles(){
        return new ResponseEntity<>(teamsService.findAllUsersWithRoles(), HttpStatus.OK);
    }

    @Operation(description = "Получить всю команду по id пользователя", summary = "Получить всю команду по id пользователя")
    @GetMapping("/users/{id}")
    public ResponseEntity<TeamFullDTO> getUsersTeam(@PathVariable Long id){
        return new ResponseEntity<>(teamsService.getTeamByUserId(id), HttpStatus.OK);
    }

    @Operation(description = "Получить все команды", summary = "Получить все команды")
    @GetMapping("/all")
    public ResponseEntity<List<TeamFullDTO>> getAllTeams(){
        return new ResponseEntity<>(teamsService.findAllTeams(), HttpStatus.OK);
    }

    @Operation(description = "Получить идентификаторы всех команд", summary = "Получить id всех команд")
    @GetMapping("/ids")
    public ResponseEntity<List<Long>> getAllTeamsIds(){
        return new ResponseEntity<>(teamsService.getTeamsIds(), HttpStatus.OK);
    }

    @Operation(description = "Добавить новую команду", summary = "Добавить новую команду")
    @PostMapping("/")
    public ResponseEntity<TeamDTO> createTeam(@RequestBody TeamDTO dto){
        return new ResponseEntity<>(teamsService.saveNewTeam(dto), HttpStatus.OK);
    }

    @Operation(description = "ОБновить команду", summary = "Оновить команду")
    @PutMapping("/")
    public ResponseEntity<TeamDTO> editTeam(@RequestBody TeamDTO dto){
        return new ResponseEntity<>(teamsService.saveNewTeam(dto), HttpStatus.OK);
    }
}
