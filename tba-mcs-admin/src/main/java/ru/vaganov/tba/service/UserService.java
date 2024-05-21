package ru.vaganov.tba.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.vaganov.tba.config.FileParser;
import ru.vaganov.tba.mapper.UserMapper;
import ru.vaganov.tba.models.SystemRoles;
import ru.vaganov.tba.models.User;
import ru.vaganov.tba.models.dto.UserDTO;
import ru.vaganov.tba.repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service @Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder encoder;
    public UserDTO saveUser(UserDTO dto){
        User user = userMapper.fromDto(dto);
        if(dto.getId() == null || !userRepository.existsById(dto.getId()))
            user.setDateRegister(LocalDateTime.now());

        user.setPassword(encoder.encode(dto.getPassword()));
        return userMapper.toDto(userRepository.save(user));
    }

    public UserDTO findUserById(Long id){
        User user = userRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Cannot find user with id:"+id));
        return userMapper.toDto(user);
    }

    public UserDTO updateUser(Long id, UserDTO dto){
        User user = userRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Cannot find user with id: "+id));

        dto.setId(null);
        userMapper.updateFromDto(dto, user);
        return userMapper.toDto(userRepository.save(user));

    }

    public List<UserDTO> findUsersByIds(List<Long> ids){
        return userMapper.toDto(userRepository.findAllByIdIn(ids));
    }

    public Long countAll(){
        return userRepository.count();
    }

    public List<UserDTO> findAll(){
        return userMapper.toDto(userRepository.findAll());
    }

    public List<UserDTO> loadFromFile(MultipartFile file){
        FileParser parser = new FileParser<UserDTO>();
        parser.exec(file,
                (str)->{
                    String[] studs = str.split(",");
                    return  UserDTO.builder()
                            .lastname(studs[0])
                            .firstname(studs[1])
                            .middlename(studs[2])
                            .email(studs[3])
                            .username(studs[4])
                            .systemRole(SystemRoles.STUDENT)
                            .academicGroup("РИ-40022")
                            .password("test")
                            .build();
                },
                (user)->saveUser((UserDTO) user));
        return findAll();
    }
}
