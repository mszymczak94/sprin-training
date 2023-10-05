package pl.training.shop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.http.HttpMethod.POST;

@Configuration
public class SecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public UserDetailsManager userDetailsManager() {
        var user = User
                .withUsername("jan")
                .password(passwordEncoder().encode("123"))
                .authorities("ROLE_ADMIN")
                // .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(config -> {})
                .authorizeHttpRequests(config -> config
                    .requestMatchers("/login.html").permitAll()
                    .requestMatchers(POST, "/payments/process").hasAuthority("ROLE_ADMIN") //hasRole("ADMIN");
                    .requestMatchers("/**").authenticated()
                )
                // .httpBasic(config -> {})
                .formLogin(config -> config
                    .loginPage("/login.html")
                    .usernameParameter("username")
                    .passwordParameter("password")
                )
                .build();
    }

   /*
    https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter

    AuthenticationManager authenticationManager; // Abstrakcja odpowiadająca za proces uwierzytelnienia
        ProviderManager providerManager; // Implementuje AuthenticationManager, deleguje uwierzytelnienie do jednego ze skonfigurowanych AuthenticationProvider
            AuthenticationProvider authenticationProvider; // Fizycznie przeprowadza uwierzytelnienie np. DaoAuthenticationProvider, LdapAuthenticationProvider, OpenIDAuthenticationProvider
                DaoAuthenticationProvider daoAuthenticationProvider; // Ładuje dane za pomocą usługi UserDetailsService i porównuje z danymi przekazanymi w Authentication


    Authentication authentication; // Reprezentuje dane niezbędne do uwierzytelnienia, ale także status po uwierzytelnieniu mplementowane np. przez UsernamePasswordAuthenticationToken
    UserDetails userDetails; // Reprezentuje użytkownika / konto
    GrantedAuthority grantedAuthority; // Reprezentuje uprawnienia / role
    SecurityContext securityContext; // Przechowuje Authentication zalogowanego użytkownika
    SecurityContextHolder securityContextHolder; // Przechowuje SecurityContext dla użytkownika (domyślna strategia ThreadLocal)
    PasswordEncoder passwordEncoder; // Abstrakcja obiektu umożliwiającego hashowanie i porównywanie haseł
        BCryptPasswordEncoder bCryptPasswordEncoder;

    // Deprecated
    AccessDecisionManager decisionManager; // Abstrakcja odpowiadająca za autoryzację dostępu np. AffirmativeBased, ConsensusBased, UnanimousBased
        // Podejmuje decyzję na podstawie głosowania AccessDecisionVoter accessDecisionVoter

    AuthorizationManager authorizationManager;
     */

}
