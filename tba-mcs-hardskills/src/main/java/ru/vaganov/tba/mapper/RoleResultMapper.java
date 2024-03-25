package ru.vaganov.tba.mapper;

import org.mapstruct.Mapper;
import ru.vaganov.tba.models.RoleResult;
import ru.vaganov.tba.models.dto.RoleResultDTO;

import java.util.List;

@Mapper(componentModel = "spring",uses = {HardRoleMapper.class})
public interface RoleResultMapper {

        public abstract RoleResult fromDto(RoleResultDTO dto);
        public abstract  RoleResultDTO toDto(RoleResult entity);
        public abstract List<RoleResultDTO> toDto(List<RoleResult> entity);
}
