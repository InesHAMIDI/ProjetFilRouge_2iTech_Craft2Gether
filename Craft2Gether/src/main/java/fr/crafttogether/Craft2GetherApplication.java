package fr.crafttogether;

import fr.crafttogether.models.Bloc;
import fr.crafttogether.models.Recette;
import fr.crafttogether.repositories.BlocRepository;
import fr.crafttogether.repositories.RecetteRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
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

import static fr.crafttogether.models.Bloc.BLOCK_TYPE.MANUFACTURE;
import static fr.crafttogether.models.Bloc.BLOCK_TYPE.NATUREL;

@SpringBootApplication
@AllArgsConstructor
@NoArgsConstructor
public class Craft2GetherApplication implements ApplicationRunner {

    //private BlocRepository blocRepository;
    //private ListeRepository listeRepository;
    //private UserRepository userRepository;
    //private RecetteRepository recetteRepository;

    public static void main(String[] args) {
        SpringApplication.run(Craft2GetherApplication.class, args);
    }
    @Value("${frontend.url}")
    private String frontendUrl;

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
