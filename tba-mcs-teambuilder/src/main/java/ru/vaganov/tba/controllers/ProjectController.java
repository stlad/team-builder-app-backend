package ru.vaganov.tba.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vaganov.tba.model.dto.ProjectDTO;
import ru.vaganov.tba.model.dto.TeamFullDTO;
import ru.vaganov.tba.service.ProjectService;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/projects")
@Tag(name = "Projects API")
@Slf4j
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Operation(description = "Получить все проекты", summary = "Получить все проекты")
    @GetMapping("/all")
    public ResponseEntity<List<ProjectDTO>> getAllProjects(){
        return new ResponseEntity<>(projectService.getAllProjects(), HttpStatus.OK);
    }

    @Operation(description = "Присоединить команду к проекту", summary = "Присоединить команду к проекту")
    @PostMapping("/attach")
    public ResponseEntity<String> attachTeamToProject(@RequestParam Long teamId, @RequestParam Long projId){
        projectService.attachTeamToProject(teamId,projId);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @Operation(description = "Добавить проект", summary = "Добавить проект")
    @PostMapping("/")
    public ResponseEntity<ProjectDTO> saveProject(@RequestBody ProjectDTO dto){
        return new ResponseEntity<>(projectService.saveNewProject(dto), HttpStatus.OK);
    }

    @Operation(description = "Количество команд проекта", summary = "Количество команд проекта")
    @GetMapping("/{id}/count")
    public ResponseEntity<Integer> getTeamsCount(@PathVariable Long id){
        return new ResponseEntity<>(projectService.countTeamsByProjectId(id), HttpStatus.OK);
    }

    @Operation(description = "Получить все команды проекта", summary = "Получить все команды проекта")
    @GetMapping("/{id}/all")
    public ResponseEntity<List<TeamFullDTO>> getTeams(@PathVariable Long id){
        return new ResponseEntity<>(projectService.getAllTeamsOfProject(id), HttpStatus.OK);
    }

    @Operation(description = "Получить проект по id", summary = "Получить проект по id")
    @GetMapping("/{id}")
    public ResponseEntity<ProjectDTO> getProjectById(@PathVariable Long id){
        return new ResponseEntity<>(projectService.getProjectById(id), HttpStatus.OK);
    }

    @Operation(description = "Получить все идентификаторы проектов", summary = "Получить все идентификаторы проектов")
    @GetMapping("/ids")
    public ResponseEntity<List<Long>> getProjectById(){
        return new ResponseEntity<>(projectService.getAllIds(), HttpStatus.OK);
    }

    @Operation(description = "Обновить новый проект", summary = "Обновить проект")
    @PutMapping("/")
    public ResponseEntity<ProjectDTO> editProject(@RequestBody ProjectDTO dto){
        return new ResponseEntity<>(projectService.saveNewProject(dto), HttpStatus.OK);
    }
}
