package pe.siniestrofacil.application;

import org.junit.jupiter.api.Test;
import pe.siniestrofacil.application.security.AuthenticatedUser;
import pe.siniestrofacil.application.security.AuthenticationContext;
import pe.siniestrofacil.application.security.AuthorizationService;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AuthorizationServiceTest {

    @Test
    void operadorPuedeAutorizar() {
        AuthenticationContext context = new AuthenticationContext();
        context.set(new AuthenticatedUser("uid-operador", "OPERADOR"));

        AuthorizationService service =
                new AuthorizationService(context);

        assertDoesNotThrow(
                service::requireAuthorizationRole);

        context.clear();
    }

    @Test
    void aseguradoNoPuedeAutorizar() {
        AuthenticationContext context = new AuthenticationContext();
        context.set(new AuthenticatedUser("uid-asegurado", "ASEGURADO"));

        AuthorizationService service =
                new AuthorizationService(context);

        assertThrows(
                SecurityException.class,
                service::requireAuthorizationRole);

        context.clear();
    }
}
