package by.artezio.hackathon.service.dto.impl;

import by.artezio.hackathon.model.User;
import by.artezio.hackathon.repository.UserRepository;
import by.artezio.hackathon.service.dto.UserRegistrationDto;
import by.artezio.hackathon.service.dto.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by rezerv on 19.03.2016.
 */
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
        return userRepository.save(user);
    }
}
