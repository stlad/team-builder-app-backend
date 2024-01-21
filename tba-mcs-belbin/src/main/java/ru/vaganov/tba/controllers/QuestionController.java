package ru.vaganov.tba.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vaganov.tba.models.dto.AnswerBlank;
import ru.vaganov.tba.service.BelbinService;
import ru.vaganov.tba.service.responses.BlockQuestionResponseDTO;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/questions")
@Tag(name = "Questions API")
@Slf4j
public class QuestionController {
    @Autowired
    private BelbinService belbinService;


    @GetMapping("/block/{number}")
    public ResponseEntity<BlockQuestionResponseDTO> getBlockByNumber(@PathVariable(value = "number") Long number){
        log.info("GET to /questions/block/"+number);
        BlockQuestionResponseDTO response = belbinService.getBlockByNumber(number);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/blank")
    public ResponseEntity<AnswerBlank> getEmptyBlank(){
        log.info("GET to /questions/blank");
        return new ResponseEntity<>(belbinService.getAnswerBlank(), HttpStatus.OK);
    }

}
