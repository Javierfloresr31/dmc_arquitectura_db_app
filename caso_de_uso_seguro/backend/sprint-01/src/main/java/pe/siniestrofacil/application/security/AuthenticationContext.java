package pe.siniestrofacil.application.security;

import org.springframework.stereotype.Component;

@Component
public class AuthenticationContext {

    private static final ThreadLocal<AuthenticatedUser> CURRENT_USER =
            new ThreadLocal<>();

    public void set(AuthenticatedUser user) {
        CURRENT_USER.set(user);
    }

    public AuthenticatedUser get() {
        return CURRENT_USER.get();
    }

    public void clear() {
        CURRENT_USER.remove();
    }
}
