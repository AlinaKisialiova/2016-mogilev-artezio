package by.artezio.hackathon.util.security;

import by.artezio.hackathon.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author ntishkevich
 * @version 19.03.2016
 */
public final class SecurityUtils {
    private SecurityUtils() {
    }

    public static UserDetails getCurrentUserDetails() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        if (authentication != null) {
            if (authentication.getPrincipal() instanceof UserDetails) {
                return (UserDetails) authentication.getPrincipal();
            }
        }
        return null;
    }

    public static User getCurrentUser() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        if (authentication != null) {
            if (authentication.getPrincipal() instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                return userDetails.getUser();
            }
        }
        return null;
    }

    public static Long getCurrentUserId() {
        return getCurrentUser().getId();
    }
}
