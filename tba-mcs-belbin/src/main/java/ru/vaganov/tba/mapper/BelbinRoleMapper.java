package ru.vaganov.tba.mapper;

import org.mapstruct.Mapper;
import ru.vaganov.tba.models.BelbinRole;
import ru.vaganov.tba.models.dto.BelbinRoleDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BelbinRoleMapper {
    public abstract BelbinRole fromDto(BelbinRoleDTO dto);
    public abstract  BelbinRoleDTO toDto(BelbinRole entity);
    public abstract List<BelbinRoleDTO> toDto(List<BelbinRole> entity);
}
