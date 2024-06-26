package ru.vaganov.tba.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vaganov.tba.AdminApiClient;
import ru.vaganov.tba.dto.UserExternalDTO;
import ru.vaganov.tba.models.dto.HardRoleWithQuotaDTO;
import ru.vaganov.tba.models.dto.RoleResultDTO;
import ru.vaganov.tba.models.dto.RoleResultShortDTO;
import ru.vaganov.tba.service.HardRolesService;
import ru.vaganov.tba.service.ResultsService;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/results")
@Tag(name = "Hard Roles Results API")
@Slf4j
public class ResultController {

    @Autowired
    private ResultsService resultsService;


    @Operation(summary = "Получение впроф. роли и сколько осталось не нее мест")
    @GetMapping("/catalog")
    public ResponseEntity<List<HardRoleWithQuotaDTO>>getRolesWithQuota(){
        return new ResponseEntity<>(resultsService.getCatalogWithQuotas(), HttpStatus.OK);
    }

    @Operation(summary = "Сохранение результата выбора роли")
    @PostMapping("/")
    public ResponseEntity<RoleResultDTO> saveResult(@RequestBody RoleResultShortDTO dto){
        return new ResponseEntity<>(resultsService.saveResult(dto), HttpStatus.OK);
    }

    @Operation(summary = "Получение результата выбора по ID пользователя")
    @GetMapping("/user/{id}")
    public ResponseEntity<RoleResultDTO> findByUserId(@PathVariable Long id){
        return new ResponseEntity<>(resultsService.findByUserId(id),HttpStatus.OK);
    }

    @Operation(summary = "Получение всего списка результатов")
    @GetMapping("/")
    public ResponseEntity<List<RoleResultShortDTO>> getAllResults(){
        return new ResponseEntity<>(resultsService.getAllUserResults(), HttpStatus.OK);
    }
}
