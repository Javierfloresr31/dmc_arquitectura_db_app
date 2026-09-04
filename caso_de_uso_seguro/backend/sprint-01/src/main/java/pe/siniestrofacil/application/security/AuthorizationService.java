package pe.siniestrofacil.application.security;

import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class AuthorizationService {

    private static final Set<String> ROLES_AUTORIZAR =
            Set.of("OPERADOR", "AJUSTADOR", "SUPERVISOR");

    private final AuthenticationContext authenticationContext;

    public AuthorizationService(
            AuthenticationContext authenticationContext) {

        this.authenticationContext = authenticationContext;
    }

    public void requireRole(Set<String> allowedRoles) {

        AuthenticatedUser user = authenticationContext.get();

        if (user == null) {
            throw new IllegalStateException(
                    "Usuario no autenticado");
        }

        if (!allowedRoles.contains(user.role())) {
            throw new SecurityException(
                    "Rol sin permiso para realizar la operación");
        }
    }

    public void requireAuthorizationRole() {
        requireRole(ROLES_AUTORIZAR);
    }
}
