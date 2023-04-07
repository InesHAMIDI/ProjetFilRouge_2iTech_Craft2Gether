package fr.crafttogether.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

public class SecurityConfig {
    /*private final RequestMatcher adminUrls = new OrRequestMatcher(
            //users
            new AntPathRequestMatcher("/users", "GET"), //get all users
            new AntPathRequestMatcher("/users/{id}", "GET"), //get user[id]
            new AntPathRequestMatcher("/users/{nom}", "GET"), //get user[nom]

            //listes
            new AntPathRequestMatcher("/listes", "GET") //get all listes
    );

    private final RequestMatcher adminOrSelfUrls = new OrRequestMatcher(
            //users
            new AntPathRequestMatcher("/users", "POST"), //save user
            new AntPathRequestMatcher("/users/{id}", "PUT"), //update user
            new AntPathRequestMatcher("/users/{id}", "DELETE"), //delete user
            new AntPathRequestMatcher("/users/{id}/listes", "GET"), //get users list

            //listes
            new AntPathRequestMatcher("/listes/{id}", "GET"), //get listes[id]
            new AntPathRequestMatcher("/listes/{nom}", "GET"), //get listes[nom]
            new AntPathRequestMatcher("/listes", "POST"), //save listes
            new AntPathRequestMatcher("/listes/{id}", "PUT"), //update listes
            new AntPathRequestMatcher("/listes/{id}", "DELETE") //delete listes
    );

    private final RequestMatcher publicUrls = new OrRequestMatcher(
            //recettes
            new AntPathRequestMatcher("/recettes", "GET"),
            new AntPathRequestMatcher("/recettes/i/{id}", "GET"),
            new AntPathRequestMatcher("/recettes/n/{nom}", "GET"),

            //blocs
            new AntPathRequestMatcher("/blocs", "GET"),
            new AntPathRequestMatcher("/blocs/i/{id}", "GET"),
            new AntPathRequestMatcher("/blocs/n/{nom}", "GET")
    );


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                .requestMatchers(adminUrls)
                .hasRole("ADMIN")
//			.requestMatchers(new AntPathRequestMatcher("/users/{id}", "GET"))
//				.access(new WebExpressionAuthorizationManager("hasRole('ADMIN') or #id == principal.getUser().getId()"))
                .requestMatchers(adminOrSelfUrls)
                .access(new WebExpressionAuthorizationManager("hasRole('ADMIN') or #id == principal.getUser().getId()"))
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
    }*/

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
