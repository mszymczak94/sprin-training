package pl.training.shop.commons.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Set;

import static pl.training.shop.commons.security.jwt.JwtConfig.*;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (authentication instanceof JwtAuthenticationToken jwtAuthentication) {
            var token = jwtAuthentication.getToken();
            try {
                var verifier = JWT.require(ALGORITHM)
                        .withIssuer(ISSUER)
                        .build();
                var decodedJWT = verifier.verify(token);
                var user = decodedJWT.getClaim(USER_CLAIM).asString();
                var role = decodedJWT.getClaim(ROLE_CLAIM).asString();
                return UsernamePasswordAuthenticationToken
                        .authenticated(user, null, Set.of(new SimpleGrantedAuthority(role)));
            } catch (JWTVerificationException exception){
                throw new BadCredentialsException("Invalid token");
            }
        } else {
            throw new IllegalStateException("Unknown authentication token");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
