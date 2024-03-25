package ru.vaganov.tba.mapper;

import org.mapstruct.Mapper;
import ru.vaganov.tba.models.HardRole;
import ru.vaganov.tba.models.dto.HardRoleDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HardRoleMapper {
        public abstract HardRole fromDto(HardRoleDTO dto);
        public abstract  HardRoleDTO toDto(HardRole entity);
        public abstract List<HardRoleDTO> toDto(List<HardRole> entity);
}
