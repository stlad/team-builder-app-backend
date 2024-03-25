package ru.vaganov.tba.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vaganov.tba.AdminApiClient;
import ru.vaganov.tba.exceptions.ValidationException;
import ru.vaganov.tba.mapper.HardRoleMapper;
import ru.vaganov.tba.mapper.RoleResultMapper;
import ru.vaganov.tba.models.HardRole;
import ru.vaganov.tba.models.RoleResult;
import ru.vaganov.tba.models.dto.HardRoleDTO;
import ru.vaganov.tba.models.dto.RoleResultDTO;
import ru.vaganov.tba.models.dto.RoleResultShortDTO;
import ru.vaganov.tba.repositories.HardRoleRepository;
import ru.vaganov.tba.repositories.RoleResultRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HardRolesService {

    @Autowired
    private HardRoleRepository hardRoleRepository;
    @Autowired
    private HardRoleMapper hardRoleMapper;

    @Autowired
    private RoleResultRepository resultRepository;
    @Autowired
    private RoleResultMapper resultMapper;

    @Autowired
    private AdminApiClient adminApiClient;

    public List<HardRoleDTO> findAllRoles(){
        return hardRoleMapper.toDto(hardRoleRepository.findAll());
    }

    public List<RoleResultDTO> findAllResultsByUser(Long userId){
        List<RoleResult> results = resultRepository.findAllByUserIdAndPositionIn(userId, List.of(1,2,3));

        return resultMapper.toDto(results);
    }

    public List<RoleResultDTO> addResults(List<RoleResultShortDTO> dtos){
        return  dtos.stream().map(this::addResult)
                .collect(Collectors.toList());
    }

    private RoleResultDTO addResult(RoleResultShortDTO shortDTO){
        HardRole role = hardRoleRepository.findById(shortDTO.getHardRoleId())
                .orElseThrow(()-> new EntityNotFoundException("Cannot find hard role with id: "+shortDTO.getHardRoleId()));
//        if(shortDTO.getUserId()==null)
//            throw new EntityNotFoundException("Cannot find user with id: "+shortDTO.getUserId());
        if(shortDTO.getPosition() > 3 || shortDTO.getPosition() < 1 || shortDTO.getPosition()==null)
            throw new ValidationException("Rating position must be in [1,3]");

        RoleResult result = resultRepository.findByUserIdAndPosition(shortDTO.getUserId(), shortDTO.getPosition())
                .orElseGet(RoleResult::new);;

        result.setPosition(shortDTO.getPosition());
        result.setUserId(shortDTO.getUserId());
        result.setHardRole(role);


        result = resultRepository.save(result);
        return resultMapper.toDto(result);
    };
}
