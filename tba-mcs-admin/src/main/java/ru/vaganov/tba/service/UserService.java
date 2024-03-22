package ru.vaganov.tba.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vaganov.tba.mapper.UserMapper;
import ru.vaganov.tba.models.User;
import ru.vaganov.tba.models.dto.UserDTO;
import ru.vaganov.tba.repositories.UserRepository;

import java.time.LocalDateTime;

@Service @Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public UserDTO saveUser(UserDTO dto){
        User user = userMapper.fromDto(dto);
        if(dto.getId() == null || !userRepository.existsById(dto.getId()))
            user.setDateRegister(LocalDateTime.now());
        return userMapper.toDto(userRepository.save(user));
    }

    public UserDTO findUserById(Long id){
        User user = userRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Cannot find user with id:"+id));
        return userMapper.toDto(user);
    }
}
