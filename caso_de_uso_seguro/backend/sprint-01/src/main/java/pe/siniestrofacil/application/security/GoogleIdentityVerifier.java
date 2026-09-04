package pe.siniestrofacil.application.security;

import com.google.api.client.json.webtoken.JsonWebSignature;
import com.google.auth.oauth2.TokenVerifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GoogleIdentityVerifier {

    private final TokenVerifier tokenVerifier;

    public GoogleIdentityVerifier(
            @Value("${security.google.audience}") String audience) {

        this.tokenVerifier = TokenVerifier.newBuilder()
                .setAudience(audience)
                .setIssuer("https://accounts.google.com")
                .build();
    }

    public String verifyAndGetSubject(String token) {
        try {
            JsonWebSignature verifiedToken = tokenVerifier.verify(token);

            if (verifiedToken.getPayload() == null
                    || verifiedToken.getPayload().getSubject() == null
                    || verifiedToken.getPayload().getSubject().isBlank()) {
                throw new IllegalArgumentException("Token sin identidad");
            }

            return verifiedToken.getPayload().getSubject();

        } catch (TokenVerifier.VerificationException e) {
            throw new IllegalArgumentException("Token de identidad inválido", e);
        }
    }
}
