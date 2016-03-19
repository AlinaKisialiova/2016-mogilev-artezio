package by.artezio.hackathon.web.validator;

import by.artezio.hackathon.service.dto.UserRegistrationDto;
import by.artezio.hackathon.service.dto.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by rezerv on 19.03.2016.
 */
public class UserRegistationValidator implements Validator{

    @Override
    public boolean supports(Class<?> clazz) {
        return UserRegistrationDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
