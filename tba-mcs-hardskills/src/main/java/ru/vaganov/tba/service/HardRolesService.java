package ru.vaganov.tba.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vaganov.tba.mapper.HardRoleMapper;
import ru.vaganov.tba.models.HardRole;
import ru.vaganov.tba.models.dto.HardRoleDTO;
import ru.vaganov.tba.repositories.HardRoleRepository;

import java.util.List;

@Service
public class HardRolesService {

    @Autowired
    private HardRoleRepository hardRoleRepository;

    @Autowired
    private HardRoleMapper hardRoleMapper;

    public List<HardRoleDTO> findAllRoles(){
        return hardRoleMapper.toDto(hardRoleRepository.findAll());
    }

}
