package pl.training.shop.commons.security;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecurityInitializer implements ApplicationRunner {

    private final JpaUserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) {
        if (repository.findByEmail("jan@training.pl").isEmpty()) {
            var user = new UserEntity();
            user.setName("jan");
            user.setEmail("jan@training.pl");
            user.setPassword(passwordEncoder.encode("123"));
            user.setEnabled(true);
            user.setRole("ROLE_ADMIN");
            repository.save(user);
        }
    }

}
