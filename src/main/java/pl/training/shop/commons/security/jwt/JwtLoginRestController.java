package pl.training.shop.commons.security.jwt;

import com.auth0.jwt.JWT;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.SECONDS;
import static pl.training.shop.commons.security.jwt.JwtConfig.*;

@RequestMapping("api/tokens")
@RestController
public class JwtLoginRestController {

    @PostMapping
    public JwtDto login(@RequestBody CredentialsDto credentialsDto) {
        // check login and password
        var token = JWT.create()
                .withIssuer(ISSUER)
                .withClaim(USER_CLAIM, credentialsDto.getLogin())
                .withClaim(ROLE_CLAIM, "ROLE_ADMIN")
                .withExpiresAt(Instant.now().plus(1, SECONDS))
                .sign(ALGORITHM);
        return new JwtDto(token);
    }

}
