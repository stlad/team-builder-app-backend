package ru.vaganov.tba.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.vaganov.tba.models.User;
import ru.vaganov.tba.models.dto.UserDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    public abstract User fromDto(UserDTO dto);
    public abstract  UserDTO toDto(User entity);
    public abstract List<UserDTO> toDto(List<User> entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateFromDto(UserDTO dto, @MappingTarget User entity);
}
