package ru.vaganov.tba.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.vaganov.tba.mapper.UserMapper;
import ru.vaganov.tba.models.User;
import ru.vaganov.tba.models.dto.UserDTO;
import ru.vaganov.tba.repositories.UserRepository;

@Service
public class AuthService {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserMapper mapper;

    public UserDTO login(UserDTO request){
        String email = request.getEmail();
        User user = userRepository.findByEmail(email).orElseThrow(()-> new EntityNotFoundException("Cannot find user with email: "+email));

        if(encoder.matches(request.getPassword(), user.getPassword())){
            throw new SecurityException("Wrong Password");
        }
        return mapper.toDto(user);
    }

    public UserDTO register(UserDTO request){
        return userService.saveUser(request);
    }
}
