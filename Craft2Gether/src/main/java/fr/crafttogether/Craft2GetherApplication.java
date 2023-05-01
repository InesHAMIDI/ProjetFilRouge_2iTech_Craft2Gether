package fr.crafttogether;

import fr.crafttogether.models.*;
import fr.crafttogether.repositories.BlocRepository;
import fr.crafttogether.repositories.ListeRepository;
import fr.crafttogether.repositories.RecetteRepository;
import fr.crafttogether.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.HashMap;
import java.util.List;

import static fr.crafttogether.models.Bloc.BLOCK_TYPE.MANUFACTURE;
import static fr.crafttogether.models.Bloc.BLOCK_TYPE.NATUREL;

@SpringBootApplication
public class Craft2GetherApplication implements ApplicationRunner {
    @Autowired
    private BlocRepository blocRepository;
    @Autowired
    private ListeRepository listeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RecetteRepository recetteRepository;
    @Value("${frontend.url}")
    private String frontendUrl;
    public static void main(String[] args) {
        SpringApplication.run(Craft2GetherApplication.class, args);
    }
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "UPDATE", "DELETE", "OPTIONS")
                        .allowedOrigins(frontendUrl);
            }

        };
    }
    @Override
    public void run(ApplicationArguments args){

    }
}
