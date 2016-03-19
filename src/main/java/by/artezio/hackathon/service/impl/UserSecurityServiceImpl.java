package by.artezio.hackathon.service.impl;

import by.artezio.hackathon.model.User;
import by.artezio.hackathon.repository.UserRepository;
import by.artezio.hackathon.service.UserSecurityService;
import by.artezio.hackathon.util.security.UserDetails;
import by.artezio.hackathon.util.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author ntishkevich
 * @version 19.03.2016
 */
@Service
public class UserSecurityServiceImpl implements UserSecurityService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        User user = ((UserDetails) event.getAuthentication().getPrincipal()).getUser();
        Date lastLoginDate = new Date();
        user.setLastEventDate(lastLoginDate);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        final User user = userRepository.findByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User with {login = %s} does not exist", login));
        }
        return new UserDetailsImpl(user);
    }
}
