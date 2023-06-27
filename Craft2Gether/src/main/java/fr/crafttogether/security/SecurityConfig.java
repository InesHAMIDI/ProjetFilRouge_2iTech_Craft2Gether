package fr.crafttogether.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import static org.springframework.security.authorization.AuthorityAuthorizationManager.hasRole;
import static org.springframework.security.authorization.AuthorizationManagers.anyOf;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {
    @Bean
    InMemoryUserDetailsManager users() {
        return new InMemoryUserDetailsManager(
                User.builder().username("admin").password("{noop}admin").roles("ADMIN", "PLAYER").build(),
                User.builder().username("user").password("{noop}user").roles("PLAYER").build());
    }

    private final RequestMatcher adminUrls = new OrRequestMatcher(
            //users
            new AntPathRequestMatcher("/users", "GET"), //get all users

            //listes
            new AntPathRequestMatcher("/listes", "GET"), //get all listes
            new AntPathRequestMatcher("/listes", "POST"), //save listes
            new AntPathRequestMatcher("/listes/{id}", "PUT"), //update listes
            new AntPathRequestMatcher("/listes/{id}", "DELETE") //delete listes
    );

    private final RequestMatcher publicUrls = new OrRequestMatcher(
            //listes
            new AntPathRequestMatcher("/listes/{id}", "GET"), //get listes[id]

            //recettes
            new AntPathRequestMatcher("/recettes", "GET"),
            new AntPathRequestMatcher("/recettes/{id}", "GET"),

            //blocs
            new AntPathRequestMatcher("/blocs", "GET"),

            //auth
            new AntPathRequestMatcher("/auth", "POST")

    );

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                    .requestMatchers(adminUrls)
                        .hasRole("ADMIN")

                    .requestMatchers(publicUrls)
                        .permitAll()

                .and()

                .formLogin().disable()
                .logout().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .httpBasic();

        return http.build();

    }
}