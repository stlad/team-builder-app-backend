package ru.vaganov.tba.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vaganov.tba.service.TeambuildingService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/tb")
@Tag(name = "Teambuilder API")
@Slf4j
public class TeamBuilderController {

    @Autowired
    private TeambuildingService teambuildingService;


    @Operation(description = "Старт процесса распределения по командам", summary = "Инициализировать распределение по командам")
    @GetMapping("/start")
    public ResponseEntity<String> buildCommands(){
        teambuildingService.buildTeams();
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}
