package ru.vaganov.tba.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vaganov.tba.BelbinApiClient;
import ru.vaganov.tba.HardskillsApiClient;
import ru.vaganov.tba.repositories.UserResultsRepository;
import ru.vaganov.tba.dto.HardskillsResultDTO;
import ru.vaganov.tba.model.UserFullResult;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeambuildingService {

    @Autowired
    private BelbinApiClient belbinApi;
    @Autowired
    private HardskillsApiClient hardskillsApi;

    @Autowired
    private UserResultsRepository userResultsRepository;

    @Autowired
    private TeamReaperNest teamReaperNest;

    public void buildTeams(){
        teamReaperNest.Reap();
    }


    public void prepareAllResults(){ // Подготовка всех результатов всех тестов. Занесение во временную таблицу
        HardskillsResultDTO[] profRoles = hardskillsApi.getAllUserResults();
        List<UserFullResult> preparedResults = new ArrayList<>();
        for(HardskillsResultDTO profResult : profRoles){
            var belbinResult = belbinApi.getUserResultById(profResult.getUserId());
            var userResult = new UserFullResult();
            userResult.setUserId(profResult.getUserId());
            userResult.setTeamRoleId(belbinResult.getRoleId());
            userResult.setProfRoleId(profResult.getRoleId());

            preparedResults.add(userResult);
        }
        userResultsRepository.saveAll(preparedResults);
    }

    public void clearAll(){
        userResultsRepository.deleteAll();
    }

}
