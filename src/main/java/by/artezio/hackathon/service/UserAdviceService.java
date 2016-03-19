package by.artezio.hackathon.service;

import by.artezio.hackathon.model.User;
import by.artezio.hackathon.model.UserAdvice;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author ntishkevich
 * @version 19.03.2016
 */
public interface UserAdviceService {

    UserAdvice findOrCreate(Long id, User user);
}
