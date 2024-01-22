package ru.vaganov.tba.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vaganov.tba.mapper.BelbinRoleMapper;
import ru.vaganov.tba.models.dto.AnswerBlank;
import ru.vaganov.tba.models.dto.ResultBlank;
import ru.vaganov.tba.repositories.BelbinRoleRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SolverService {

    @Autowired
    private BelbinRoleRepository belbinRoleRepository;
    @Autowired
    private BelbinService belbinService;
    @Autowired
    private BelbinRoleMapper roleMapper;

    public static final Integer BELBIN_TEST_POINT_SUM = 70;

    public AnswerBlank getAnswerBlank(){
        Map<String, Integer> blank = new HashMap<>();
        belbinRoleRepository.findAll().forEach(belbinRole -> blank.put(belbinRole.getEngName(), 0));
        return new AnswerBlank(blank);
    }

    public ResultBlank getTestResult(AnswerBlank answerBlank){
        checkSum(answerBlank);
        Map<String, Integer> blank = answerBlank.getBlank();
        ResultBlank resultBlank = new ResultBlank();
        blank.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .forEach(pair->{
                    resultBlank.add(belbinService.getBelbinRoleByName(pair.getKey()), pair.getValue());
                });
        return resultBlank;
    }

    private void checkSum(AnswerBlank answerBlank){
        Map<String, Integer> blank = answerBlank.getBlank();
        Integer sum = blank.values().stream().mapToInt(d-> d).sum();
        if(!sum.equals(BELBIN_TEST_POINT_SUM)){
            throw new IllegalArgumentException("Incorrect point sum. Must be: "+BELBIN_TEST_POINT_SUM+". Your: " + sum);
        }
    }
}
