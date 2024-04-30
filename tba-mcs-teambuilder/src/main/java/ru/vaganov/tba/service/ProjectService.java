package ru.vaganov.tba.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vaganov.tba.mapper.ProjectMapper;
import ru.vaganov.tba.model.Project;
import ru.vaganov.tba.model.Team;
import ru.vaganov.tba.model.dto.ProjectDTO;
import ru.vaganov.tba.model.dto.TeamFullDTO;
import ru.vaganov.tba.repositories.ProjectRepository;
import ru.vaganov.tba.repositories.TeamRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private TeamsService teamsService;

    public ProjectDTO findProjectOfTeam(Long teamId){
        Team team = teamRepository.findById(teamId)
                .orElseThrow(()-> new EntityNotFoundException("Cannot find Team with id: "+teamId));

        return projectMapper.toDTO(team.getAttachedProject());
    }

    public ProjectDTO saveNewProject(ProjectDTO dto){
        Project project = projectMapper.fromDTO(dto);
        return projectMapper.toDTO(projectRepository.save(project));
    }

    public void attachTeamToProject(Long teamId, Long projId){
        Team team = teamRepository.findById(teamId)
                .orElseThrow(()-> new EntityNotFoundException("Cannot find Team with id: "+teamId));
        Project project = projectRepository.findById(projId)
                .orElseThrow(()-> new EntityNotFoundException("Cannot find Project with id: "+projId));

        team.setAttachedProject(project);
        teamRepository.save(team);
    }

    public List<ProjectDTO> getAllProjects(){
        return projectMapper.toDTO(projectRepository.findAll());
    }

    public ProjectDTO getProjectById(Long id){
        Project project = projectRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Cannot find Project with id: "+id));
        ProjectDTO dto =  projectMapper.toDTO(project);
        dto.setTeamsCount(teamRepository.countAllByAttachedProject_Id(dto.getId()));
        return dto;
    }

    public List<TeamFullDTO> getAllTeamsOfProject(Long projId){
        List<Team> teams = teamRepository.findAllByAttachedProject_Id(projId);
        return teams.stream()
                .map(Team::getId)
                .map(teamId->teamsService.getTeamFullDTO(teamId)).collect(Collectors.toList());

    }

    public Integer countTeamsByProjectId(Long projectId){
        return teamRepository.countAllByAttachedProject_Id(projectId);
    }

    public List<Long> getAllIds(){
        return projectRepository.getAllProjectIds();
    }
}
