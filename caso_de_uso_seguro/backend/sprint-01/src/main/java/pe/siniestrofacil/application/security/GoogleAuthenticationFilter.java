package pe.siniestrofacil.application.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class GoogleAuthenticationFilter extends OncePerRequestFilter {

    private final GoogleIdentityVerifier identityVerifier;
    private final AuthenticationContext authenticationContext;
    private final String defaultRole;

    public GoogleAuthenticationFilter(
            GoogleIdentityVerifier identityVerifier,
            AuthenticationContext authenticationContext,
            @Value("${security.google.default-role:OPERADOR}") String defaultRole) {

        this.identityVerifier = identityVerifier;
        this.authenticationContext = authenticationContext;
        this.defaultRole = defaultRole;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String authorization = request.getHeader("Authorization");

        if (authorization == null || !authorization.startsWith("Bearer ")) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Autenticación requerida");
            return;
        }

        String token = authorization.substring("Bearer ".length()).trim();

        if (token.isBlank()) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token de identidad requerido");
            return;
        }

        try {
            String uid = identityVerifier.verifyAndGetSubject(token);

            authenticationContext.set(
                    new AuthenticatedUser(uid, defaultRole));

            filterChain.doFilter(request, response);

        } catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token de identidad inválido");

        } finally {
            authenticationContext.clear();
        }
    }
}
