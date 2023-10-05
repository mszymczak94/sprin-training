package pl.training.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import pl.training.shop.commons.security.jwt.JwtAuthenticationFilter;
import pl.training.shop.commons.security.jwt.JwtAuthenticationToken;

import javax.sql.DataSource;

import static org.springframework.http.HttpMethod.POST;

@EnableMethodSecurity(jsr250Enabled = true, securedEnabled = true, prePostEnabled = true)
@Configuration
public class SecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    /*@Bean
    public UserDetailsManager userDetailsManager() {
        var user = User
                .withUsername("jan")
                .password(passwordEncoder().encode("123"))
                .authorities("ROLE_ADMIN")
                // .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user);
    }*/

    /*@Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        var manager = new JdbcUserDetailsManager(dataSource);
        manager.setUsersByUsernameQuery("select username, password, enabled from app_users where username = ?");
        manager.setAuthoritiesByUsernameQuery("select username, authority from app_users_authorities where username = ?");
        return manager;
    }*/

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .addFilterAt(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                //.userDetailsService()
                .csrf(config -> config
                    .ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**"))
                    .ignoringRequestMatchers(new AntPathRequestMatcher("/api/**"))
                )
                .authorizeHttpRequests(config -> config
                     .requestMatchers(new AntPathRequestMatcher("/api/tokens")).permitAll()
                     .requestMatchers(new AntPathRequestMatcher("/api/**")).hasRole("ADMIN")
                )
                // .httpBasic(config -> {})
                .formLogin(config -> config
                    .loginPage("/login.html")
                    // .usernameParameter("username")
                    // .passwordParameter("password")
                )
                .logout(config -> config
                   .logoutRequestMatcher(new AntPathRequestMatcher("/logout.html"))
                   .logoutSuccessUrl("/login.html")
                   // .invalidateHttpSession(true)
                )
                .headers(config -> config
                   .frameOptions(FrameOptionsConfig::disable)
                )
                .build();
    }

   /*
    https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter

    AuthenticationManager authenticationManager; // Abstrakcja odpowiadająca za proces uwierzytelnienia
        ProviderManager providerManager; // Implementuje AuthenticationManager, deleguje uwierzytelnienie do jednego ze skonfigurowanych AuthenticationProvider
            AuthenticationProvider authenticationProvider; // Fizycznie przeprowadza uwierzytelnienie np. DaoAuthenticationProvider, LdapAuthenticationProvider, OpenIDAuthenticationProvider
                DaoAuthenticationProvider daoAuthenticationProvider; // Ładuje dane za pomocą usługi UserDetailsService i porównuje z danymi przekazanymi w Authentication


    Authentication authentication; // Reprezentuje dane niezbędne do uwierzytelnienia, ale także status po uwierzytelnieniu implementowane np. przez UsernamePasswordAuthenticationToken
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
