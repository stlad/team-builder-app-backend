package ru.vaganov.tba.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.vaganov.tba.AdminApiClient;
import ru.vaganov.tba.BelbinApiClient;
import ru.vaganov.tba.HardskillsApiClient;
import ru.vaganov.tba.dto.BelbinRoleExternalDTO;
import ru.vaganov.tba.dto.HardRoleExternalDTO;
import ru.vaganov.tba.dto.UserExternalDTO;
import ru.vaganov.tba.model.UserFullResult;
import ru.vaganov.tba.model.dto.UserFullDTO;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapperImpl {

    @Autowired private BelbinApiClient belbinApiClient;
    @Autowired private HardskillsApiClient hardskillsApiClient;
    @Autowired private AdminApiClient adminApiClient;

    public UserFullDTO toUserFullDTO(UserFullResult entity){
        UserExternalDTO userDTO = adminApiClient.getUserById(entity.getUserId());
        if(userDTO == null) return null;
        HardRoleExternalDTO hardRoleExternalDTO = hardskillsApiClient.getRoleById(entity.getProfRoleId());
        BelbinRoleExternalDTO belbinRoleExternalDTO = belbinApiClient.getRoleById(entity.getTeamRoleId());

        UserFullDTO dto =  new UserFullDTO();
        dto.setId(userDTO.getId());
        dto.setFirstname(userDTO.getFirstname());
        dto.setLastname(userDTO.getLastname());
        dto.setDateRegister(userDTO.getDateRegister());
        dto.setAcademicGroup(userDTO.getAcademicGroup());
        dto.setProfRole(hardRoleExternalDTO);
        dto.setTeamRole(belbinRoleExternalDTO);
        dto.setTeamId(entity.getTeam() == null ? null : entity.getTeam().getId());
        return dto;
    }

    public List<UserFullDTO> toUsserFullDTOs(List<UserFullResult> resultList){
        return resultList.stream().map(this::toUserFullDTO).collect(Collectors.toList());
    }
}
