package fr.crafttogether;

import fr.crafttogether.models.Bloc;
import fr.crafttogether.models.Recette;
import fr.crafttogether.models.User;
import fr.crafttogether.models.UserRole;
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
        Bloc bois = Bloc.builder().nom("bois").outilNecessaire("hache").type(NATUREL).build();
        Bloc pierre = Bloc.builder().nom("pierre").outilNecessaire("pioche").type(NATUREL).build();
        Bloc pierreTailleeBL = Bloc.builder().nom("pierreTaillee").outilNecessaire("pioche").type(MANUFACTURE).build();
        Bloc plancheBL = Bloc.builder().nom("plancheBL").outilNecessaire("hache").type(MANUFACTURE).build();

        HashMap<Integer, Bloc> quantite1 = new HashMap<>();
        quantite1.put(1, pierre);

        HashMap<Integer, Bloc> quantite2 = new HashMap<>();
        quantite2.put(1, bois);

        Recette pierreTaillee = Recette.builder().nom("pierreTaillee").ingredients(quantite1).resultat(pierreTailleeBL).build();
        Recette planche = Recette.builder().nom("planche").ingredients(quantite2).resultat(plancheBL).build();

        recetteRepository.save(pierreTaillee);
        recetteRepository.save(planche);
        blocRepository.save(bois);
        blocRepository.save(pierre);
        blocRepository.save(pierreTailleeBL);
        blocRepository.save(plancheBL);
        
        userRepository.save(User.builder().username("admin").role(UserRole.ADMIN).password("admin").build());
    }
}
