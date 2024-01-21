package ru.vaganov.tba.Solver;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ru.vaganov.tba.models.dto.AnswerBlank;
import ru.vaganov.tba.service.SolverService;

@WebAppConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class SolverTests {

    @Autowired
    private SolverService solverService;

    @Test
    @DisplayName("Исключение, когда сумма очков не равна 70")
    void getTestResult_Throws_WhenPointLess70InSinglePos(){

        AnswerBlank blank = solverService.getAnswerBlank();
        blank.getBlank().put("Plant", 65);

        Assertions.assertThrows(IllegalArgumentException.class,
                ()-> solverService.getTestResult(blank));
    }

    @Test
    @DisplayName("Исключение, когда сумма очков не равна 70 по нескольким ячейкам")
    void getTestResult_Throws_WhenPointGreater70InMultiplyPos(){

        AnswerBlank blank = solverService.getAnswerBlank();
        blank.getBlank().put("Plant", 65);
        blank.getBlank().put("Coordinator", 65);

        Assertions.assertThrows(IllegalArgumentException.class,
                ()-> solverService.getTestResult(blank));
    }

    @Test
    @DisplayName("Исключение, когда сумма очков не равна 70")
    void getTestResult_Throws_WhenPointEqual70InSinglePos(){

        AnswerBlank blank = solverService.getAnswerBlank();
        blank.getBlank().put("Plant", 70);

        Assertions.assertDoesNotThrow(()-> solverService.getTestResult(blank));
    }

    @Test
    @DisplayName("Исключение, когда сумма очков равна 70 по нескольким ячейкам")
    void getTestResult_Throws_WhenPointEqual70InMultiplyPos(){

        AnswerBlank blank = solverService.getAnswerBlank();
        blank.getBlank().put("Plant", 10);
        blank.getBlank().put("Coordinator", 30);
        blank.getBlank().put("Resource Investigator", 30);

        Assertions.assertDoesNotThrow(()-> solverService.getTestResult(blank));
    }
}
