package ru.vaganov.tba.controllers;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vaganov.tba.model.UserFullResult;
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
}