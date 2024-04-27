package ru.vaganov.tba.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vaganov.tba.mapper.UserMapperImpl;
import ru.vaganov.tba.model.Team;
import ru.vaganov.tba.model.UserFullResult;
import ru.vaganov.tba.model.dto.TeamFullDTO;
import ru.vaganov.tba.model.dto.UserFullDTO;
import ru.vaganov.tba.repositories.TeamRepository;
import ru.vaganov.tba.repositories.UserResultsRepository;

import java.util.List;

@Service
public class TeamsService {
    @Autowired
    private UserResultsRepository userResultsRepository;

    @Autowired
    private UserMapperImpl userMapper;
    @Autowired
    private TeamRepository teamRepository;

    public List<UserFullResult> getMembersByTeamId(Long teamiD){
        return userResultsRepository.findAllByTeam_Id(teamiD);
    }

    public TeamFullDTO getTeamFullDTO(Long teamId){
        Team team = teamRepository.findById(teamId).orElseThrow(
                ()-> new EntityNotFoundException("Cannot find team with id: "+teamId )
        );

        List<UserFullResult> userFullResults = userResultsRepository.findAllByTeam_Id(teamId);
        List<UserFullDTO> fullDTOS = userMapper.toUsserFullDTOs(userFullResults);

        TeamFullDTO teamFullDTO = new TeamFullDTO();
        teamFullDTO.setId(team.getId());
        teamFullDTO.setTeamName(team.getTeamName());
        teamFullDTO.setAttachedProject(team.getAttachedProject());
        teamFullDTO.setMembers(fullDTOS);
        return teamFullDTO;
    }
}
