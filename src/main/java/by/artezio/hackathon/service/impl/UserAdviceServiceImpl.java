package by.artezio.hackathon.service.impl;

import by.artezio.hackathon.model.Advice;
import by.artezio.hackathon.model.User;
import by.artezio.hackathon.model.UserAdvice;
import by.artezio.hackathon.repository.UserAdviceRepository;
import by.artezio.hackathon.service.UserAdviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ntishkevich
 * @version 19.03.2016
 */
@Service
@Transactional
public class UserAdviceServiceImpl implements UserAdviceService {

    @Autowired
    private UserAdviceRepository userAdviceRepository;

    @Override
    public UserAdvice findOrCreate(Long adviceId, User user) {
        Advice advice = new Advice();
        advice.setId(adviceId);
        UserAdvice userAdvice = userAdviceRepository.findOneByAdviceAndUser(advice, user);
        if (userAdvice == null) {
            userAdvice = new UserAdvice();
            userAdvice.setAdvice(advice);
            userAdvice.setUser(user);
            userAdvice.setWeight(1);
        }
        userAdvice.setWeight(userAdvice.getWeight() + 1);
        return userAdviceRepository.save(userAdvice);
    }
}
