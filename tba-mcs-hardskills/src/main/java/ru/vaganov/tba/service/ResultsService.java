package ru.vaganov.tba.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vaganov.tba.exceptions.ValidationException;
import ru.vaganov.tba.mapper.HardRoleMapper;
import ru.vaganov.tba.mapper.RoleResultMapper;
import ru.vaganov.tba.models.HardRole;
import ru.vaganov.tba.models.RoleResult;
import ru.vaganov.tba.models.dto.HardRoleWithQuotaDTO;
import ru.vaganov.tba.models.dto.RoleResultDTO;
import ru.vaganov.tba.models.dto.RoleResultShortDTO;
import ru.vaganov.tba.repositories.HardRoleRepository;
import ru.vaganov.tba.repositories.RoleResultRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service @Slf4j
public class ResultsService {
    @Autowired
    private RoleResultMapper resultMapper;
    @Autowired
    private RoleResultRepository resultRepository;

    @Autowired
    private HardRoleRepository roleRepository;
    @Autowired
    private HardRoleMapper roleMapper;

    private final Integer QUOTA_PER_ROLE = 5;

    public RoleResultDTO saveResult(RoleResultShortDTO dto){
        if(dto.getHardRoleId()==null)
            throw new ValidationException("Given Role ID must not be null");
        HardRole role = roleRepository.findById(dto.getHardRoleId())
                .orElseThrow(()->new EntityNotFoundException("Cannot find role with id: "+dto.getHardRoleId()));
        if(getVacationsLeftPerRole(role.getId())<=0)
            throw new ValidationException("For Role with id "+dto.getHardRoleId()+" no vacations left");

        RoleResult result = resultRepository.findByUserId(dto.getUserId()).orElseGet(RoleResult::new);
        result.setUserId(dto.getUserId());
        result.setHardRole(role);

        return resultMapper.toDto(resultRepository.save(result));
    }

    public RoleResultDTO findByUserId(Long id){
        RoleResult result = resultRepository.findByUserId(id).orElseGet(RoleResult::new);
        return resultMapper.toDto(result);
    }

    public List<HardRoleWithQuotaDTO> getCatalogWithQuotas(){
        return roleRepository.findAll().stream().map(role -> {
            return new HardRoleWithQuotaDTO(roleMapper.toDto(role), getQuotaPerRole(role.getId()), resultRepository.countByHardRole_Id(role.getId()));
        }).collect(Collectors.toList());
    }

    private Integer getQuotaPerRole(Long roleId){
        return QUOTA_PER_ROLE;
    }

    private Integer getVacationsLeftPerRole(Long roleID){
        return getQuotaPerRole(roleID) - resultRepository.countByHardRole_Id(roleID);
    }
}
