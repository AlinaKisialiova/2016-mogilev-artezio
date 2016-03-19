package by.artezio.hackathon.web.validator;

import by.artezio.hackathon.service.dto.UserRegistrationDto;
import by.artezio.hackathon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.thymeleaf.util.StringUtils;

/**
 * Created by AlinaKisyaliova on 19.03.2016.
 */
@Service
public class UserRegistrationValidator implements Validator {

    @Autowired
    UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserRegistrationDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "retryPassword", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "field.required");

        UserRegistrationDto dto = (UserRegistrationDto) target;

        if (dto.getLogin().length() < 3) {
            errors.rejectValue("login", "field.length");
        }

        if (!StringUtils.equals(dto.getPassword(), dto.getRetryPassword())) {
            errors.rejectValue("password", "user.passwords.not.equal", "Пароли не совпадают!");
        }

        if (dto.getPassword().length() < 3) {
            errors.rejectValue("password", "field.length");
        }

        if (dto.getPassword().length() < 3) {
            errors.rejectValue("retryPassword", "field.length");
        }

        if (userService.isUserWithEmailOrLoginExist(dto)) {
            errors.reject("user.already.exists", "Пользователь с таким логином или почтой уже существует!");
        }

    }
}
