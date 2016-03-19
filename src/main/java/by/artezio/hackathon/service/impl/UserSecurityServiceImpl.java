package by.artezio.hackathon.service.impl;

import by.artezio.hackathon.model.User;
import by.artezio.hackathon.repository.UserRepository;
import by.artezio.hackathon.service.UserSecurityService;
import by.artezio.hackathon.util.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        //todo persist last event date after success auth
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
