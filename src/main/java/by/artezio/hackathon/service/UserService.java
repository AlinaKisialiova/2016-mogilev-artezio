package by.artezio.hackathon.service;

import by.artezio.hackathon.model.User;
import by.artezio.hackathon.service.dto.UserRegistrationDto;

/**
 * Created by rezerv on 19.03.2016.
 */
public interface UserService {

    User register(UserRegistrationDto dto);

    Boolean isUserWithEmailOrLoginExist(UserRegistrationDto dto);

    void decreaseUsersMood();
}
