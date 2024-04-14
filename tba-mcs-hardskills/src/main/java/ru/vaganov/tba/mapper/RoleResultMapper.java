package ru.vaganov.tba.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.vaganov.tba.models.RoleResult;
import ru.vaganov.tba.models.dto.RoleResultDTO;
import ru.vaganov.tba.models.dto.RoleResultShortDTO;

import java.util.List;

@Mapper(componentModel = "spring",uses = {HardRoleMapper.class})
public interface RoleResultMapper {

        public abstract RoleResult fromDto(RoleResultDTO dto);
        public abstract  RoleResultDTO toDto(RoleResult entity);
        public abstract List<RoleResultDTO> toDto(List<RoleResult> entity);

        @Mapping(source = "entity.hardRole.id", target = "hardRoleId")
        public abstract RoleResultShortDTO toShortDto(RoleResult entity);

        public abstract List<RoleResultShortDTO> toShortDtos(List<RoleResult> entity);
}
