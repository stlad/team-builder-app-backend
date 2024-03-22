package ru.vaganov.tba.mapper;

import org.mapstruct.Mapper;
import ru.vaganov.tba.models.User;
import ru.vaganov.tba.models.dto.UserDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    public abstract User fromDto(UserDTO dto);
    public abstract  UserDTO toDto(User entity);
    public abstract List<UserDTO> toDto(List<User> entity);
}
