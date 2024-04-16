package ru.vaganov.tba.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vaganov.tba.model.UserFullResult;
import ru.vaganov.tba.repositories.UserResultsRepository;

import java.util.List;

@Service
public class TeamsService {
    @Autowired
    private UserResultsRepository userResultsRepository;

    public List<UserFullResult> getMembersByTeamId(Long teamiD){
        return userResultsRepository.findAllByTeam_Id(teamiD);
    }
}
