package ru.vaganov.tba.service;

import jakarta.persistence.EntityNotFoundException;
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


    public HardRoleDTO findRoleByName(String name){
        HardRole role = hardRoleRepository.findByRusNameOrEngName(name, name)
                .orElseThrow(()-> new EntityNotFoundException("Cannot find role with name: "+name));
        return hardRoleMapper.toDto(role);
    }

    public HardRoleDTO findRoleById(Long id){
        HardRole role = hardRoleRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Cannot find role with id: "+id));
        return hardRoleMapper.toDto(role);
    }
}
