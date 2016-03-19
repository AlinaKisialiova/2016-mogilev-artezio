package by.artezio.hackathon.service;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author ntishkevich
 * @version 19.03.2016
 */
public interface UserSecurityService extends UserDetailsService, ApplicationListener<AuthenticationSuccessEvent> {
}
