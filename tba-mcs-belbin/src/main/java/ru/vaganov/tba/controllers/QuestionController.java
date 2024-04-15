package ru.vaganov.tba.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vaganov.tba.models.dto.AnswerBlank;
import ru.vaganov.tba.models.dto.ResultBlank;
import ru.vaganov.tba.service.BelbinService;
import ru.vaganov.tba.service.SolverService;
import ru.vaganov.tba.service.responses.BlockQuestionResponseDTO;

import java.time.LocalDateTime;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/questions")
@Tag(name = "Questions API")
@Slf4j
public class QuestionController {
    @Autowired
    private BelbinService belbinService;

    @Autowired
    private SolverService solverService;

    @Operation(summary = "Получение блока с вопросами по его номеру", description = "Получение блока с вопросами по его номеру")
    @GetMapping("/block/{number}")
    public ResponseEntity<BlockQuestionResponseDTO> getBlockByNumber(@PathVariable(value = "number") Long number){
        BlockQuestionResponseDTO response = belbinService.getBlockByNumber(number);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @Operation(summary = "Получение пустого бланка ответов", description = "Получение пустого бланка ответов")
    @GetMapping("/blank")
    public ResponseEntity<AnswerBlank> getEmptyBlank(){
        return new ResponseEntity<>(solverService.getAnswerBlank(), HttpStatus.OK);
    }


    @Operation(summary = "Отправка бланка ответов и получение результатов теста Белбина", description = "Подсчет результатов теста")
    @PostMapping("/blank")
    public ResponseEntity<ResultBlank> getAnswers(@RequestBody AnswerBlank answerBlank){
        ResultBlank resultBlank = solverService.getTestResult(answerBlank);
        return new ResponseEntity<>(resultBlank, HttpStatus.OK);
    }
}
