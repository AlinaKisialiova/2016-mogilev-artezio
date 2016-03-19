package by.artezio.hackathon.service.impl;

import by.artezio.hackathon.model.User;
import by.artezio.hackathon.repository.UserRepository;
import by.artezio.hackathon.service.dto.UserRegistrationDto;
import by.artezio.hackathon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by rezerv on 19.03.2016.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User register(UserRegistrationDto dto) {
        User user = new User();
        user.setLogin(dto.getLogin());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setEmail(dto.getEmail());
        user.setMood(0d);
        user.setBirthDate(dto.getBirthDate());
        user.setGender(dto.getGender());
        return userRepository.save(user);
    }

    @Override
    public Boolean isUserWithEmailOrLoginExist(UserRegistrationDto dto) {
        return userRepository.isUserWithLoginOrEmailExists(dto.getLogin(), dto.getEmail());
    }
}
