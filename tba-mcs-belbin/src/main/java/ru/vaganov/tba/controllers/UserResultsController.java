package ru.vaganov.tba.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vaganov.tba.models.dto.UserResultDTO;
import ru.vaganov.tba.service.SolverService;
import ru.vaganov.tba.service.UserResultsService;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/results")
@Tag(name = "UserResults API")
@Slf4j
public class UserResultsController {

    @Autowired
    private UserResultsService userResultsService;

    @PostMapping("/")
    public ResponseEntity<UserResultDTO> saveResult(
            @RequestParam Long userId,
            @RequestParam Long roleId)
    {
        return new ResponseEntity<>(userResultsService.saveUserResults(userId, roleId), HttpStatus.OK);
    }

    @GetMapping("{userId}")
    public  ResponseEntity<UserResultDTO> getResult(@PathVariable Long userId){
        return new ResponseEntity<>(userResultsService.getResult(userId), HttpStatus.OK);
    }
}
