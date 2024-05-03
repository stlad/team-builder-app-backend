package ru.vaganov.tba.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vaganov.tba.AdminApiClient;
import ru.vaganov.tba.dto.UserExternalDTO;
import ru.vaganov.tba.mapper.TeamMapper;
import ru.vaganov.tba.mapper.UserMapperImpl;
import ru.vaganov.tba.model.Team;
import ru.vaganov.tba.model.UserFullResult;
import ru.vaganov.tba.model.dto.TeamDTO;
import ru.vaganov.tba.model.dto.TeamFullDTO;
import ru.vaganov.tba.model.dto.UserFullDTO;
import ru.vaganov.tba.repositories.TeamRepository;
import ru.vaganov.tba.repositories.UserResultsRepository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamsService {
    @Autowired
    private UserResultsRepository userResultsRepository;
    @Autowired
    private AdminApiClient adminApiClient;
    @Autowired
    private UserMapperImpl userMapper;
    @Autowired
    private TeamMapper teamMapper;
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

    public List<UserFullDTO> findAllUsersWithRoles(){
         var ids = Arrays.stream(adminApiClient.getAllUsers()).map(UserExternalDTO::getId).toList();
         List<UserFullResult> userFullResults = userResultsRepository.findAllByUserIdIn(ids);
         List<UserFullDTO> fullDTOS = userMapper.toUsserFullDTOs(userFullResults);
        return fullDTOS;
    }

    public TeamFullDTO getTeamByUserId(Long userId){
        UserFullResult result = userResultsRepository.findByUserId(userId)
                .orElseThrow(()-> new EntityNotFoundException("Cannot find user with id: "+userId));
        if(result.getTeam() == null)
            return null;
        return getTeamFullDTO(result.getTeam().getId());
    }

    public List<TeamFullDTO> findAllTeams(){
        List<Long> teamIds = teamRepository.findAll().stream().map(Team::getId).toList();
        return teamIds.stream().map(this::getTeamFullDTO).collect(Collectors.toList());
    }

    public List<Long> getTeamsIds(){
        return teamRepository.findAll().stream().map(Team::getId).toList();
    }

    public TeamDTO saveNewTeam(TeamDTO dto){
        Team team = teamRepository.save(teamMapper.fromDTO(dto));
        return teamMapper.toDTO(team);
    }
}
