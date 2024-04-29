package ru.vaganov.tba.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vaganov.tba.model.UserFullResult;
import ru.vaganov.tba.model.dto.TeamFullDTO;
import ru.vaganov.tba.model.dto.UserFullDTO;
import ru.vaganov.tba.service.TeambuildingService;
import ru.vaganov.tba.service.TeamsService;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/teams")
@Tag(name = "Teambuilder API")
@Slf4j
public class TeamsController {

    @Autowired
    private TeambuildingService teambuildingService;
    @Autowired
    private TeamsService teamsService;


    @GetMapping("/{teamId}")
    public ResponseEntity<List<UserFullResult>> buildCommands(@PathVariable Long teamId){
        return new ResponseEntity<>(teamsService.getMembersByTeamId(teamId), HttpStatus.OK);
    }

    @GetMapping("/{teamId}/full")
    public ResponseEntity<TeamFullDTO> getFullTeams(@PathVariable Long teamId){
        return new ResponseEntity<>(teamsService.getTeamFullDTO(teamId), HttpStatus.OK);
    }

    @GetMapping("/users/all")
    public ResponseEntity<List<UserFullDTO>> getAllUsersWithRoles(){
        return new ResponseEntity<>(teamsService.findAllUsersWithRoles(), HttpStatus.OK);
    }

    @Operation(description = "Получить всю команду по id пользователя", summary = "Получить всю команду по id пользователя")
    @GetMapping("/users/{id}")
    public ResponseEntity<TeamFullDTO> getUsersTeam(@PathVariable Long id){
        return new ResponseEntity<>(teamsService.getTeamByUserId(id), HttpStatus.OK);
    }
}
