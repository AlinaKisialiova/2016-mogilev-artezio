package by.artezio.hackathon.service.dto;

import by.artezio.hackathon.model.User;

/**
 * Created by rezerv on 19.03.2016.
 */
public interface UserService {
    User register(UserRegistrationDto dto);
}
