package fr.crafttogether.security;

import static org.springframework.security.authorization.AuthorizationManagers.anyOf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import static org.springframework.security.authorization.AuthorityAuthorizationManager.hasRole;

import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final RequestMatcher adminUrls = new OrRequestMatcher(
            //users
            new AntPathRequestMatcher("/users", "GET"), //get all users
            new AntPathRequestMatcher("/users/{id}", "GET"), //get user[id]
            new AntPathRequestMatcher("/users/{nom}", "GET"), //get user[nom]

            //listes
            new AntPathRequestMatcher("/listes", "GET"), //get all listes

            //users
            new AntPathRequestMatcher("/users", "POST"), //save user
            new AntPathRequestMatcher("/users/{id}", "PUT"), //update user
            new AntPathRequestMatcher("/users/{id}", "DELETE"), //delete user
            new AntPathRequestMatcher("/users/{id}/listes", "GET"), //get users list

            //listes
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
            new AntPathRequestMatcher("/blocs", "GET")
    );


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                .requestMatchers(adminUrls)
                .hasRole("ADMIN")
                .requestMatchers("/users/{id}/**")
                .access(anyOf(hasRole("ADMIN"), (auth, req) ->
                        new AuthorizationDecision(((MyUserDetails) auth.get().getPrincipal()).getUser().getId() == Long.parseLong(req.getVariables().get("id")))))
                .requestMatchers(publicUrls)
                .permitAll()
                .requestMatchers("/**")
                .denyAll()
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
