package fr.crafttogether.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {
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

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}