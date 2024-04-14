package ru.vaganov.tba.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vaganov.tba.mapper.UserResultMapper;
import ru.vaganov.tba.models.BelbinRole;
import ru.vaganov.tba.models.UserResult;
import ru.vaganov.tba.models.dto.BelbinRoleDTO;
import ru.vaganov.tba.models.dto.UserResultDTO;
import ru.vaganov.tba.models.dto.UserResultShortDTO;
import ru.vaganov.tba.repositories.BelbinRoleRepository;
import ru.vaganov.tba.repositories.UserResultsRepository;

import java.util.List;

@Service
public class UserResultsService {

    @Autowired
    private UserResultMapper resultMapper;
    @Autowired
    private UserResultsRepository resultsRepository;
    @Autowired
    private BelbinRoleRepository belbinRoleRepository;

    public UserResultDTO saveUserResults(Long userId, Long roleId){
        BelbinRole role = belbinRoleRepository.findById(roleId).orElseThrow(()-> new EntityNotFoundException("cannot find role with id: "+roleId));
        UserResult result = resultsRepository.findByUserId(userId).orElseGet(()-> UserResult.builder().userId(userId).build());
        result.setRole(role);
        return resultMapper.toDto(resultsRepository.save(result));
    }

    public UserResultDTO getResult(Long userId){
        UserResult result = resultsRepository.findByUserId(userId).orElseGet(()-> UserResult.builder().userId(userId).build());
        return resultMapper.toDto(result);
    }

    public List<UserResultShortDTO> getAllResults(){
        return resultMapper.toShortDtos(resultsRepository.findAll());
    }
}

