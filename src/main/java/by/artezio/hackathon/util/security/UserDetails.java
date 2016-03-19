package by.artezio.hackathon.util.security;

import by.artezio.hackathon.model.User;

/**
 * @author ntishkevich
 * @version 19.03.2016
 */
public interface UserDetails extends org.springframework.security.core.userdetails.UserDetails {

    User getUser();
}
