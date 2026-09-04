package pe.siniestrofacil.application.security;

public record AuthenticatedUser(
        String uid,
        String role) {
}
