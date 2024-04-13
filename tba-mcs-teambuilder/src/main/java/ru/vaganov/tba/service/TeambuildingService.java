package ru.vaganov.tba.service;

import org.springframework.stereotype.Service;
import ru.vaganov.tba.model.UserFullResult;

@Service
public class TeambuildingService {


    public void prepareAllResults(){ // Подготовка всех результатов всех тестов. Занесение во временную таблицу
        var profRole = hardkillsApi.findAll();
        for(ProfRoleResult presult : hardRole){
            var teamRole = belbinApi.findByResultByuserId(presult.getUserId());
            var userResult = new UserFullResult();
            userResult.setUserId(profRole.getUserId());
            userResult.setTeamRoleId(teamRole.getRoleId());
            userResult.setProfRoleId(profRole.getRoleId());

            userResult = userFullResultRepository.save(userResult);
        }
    }



}
