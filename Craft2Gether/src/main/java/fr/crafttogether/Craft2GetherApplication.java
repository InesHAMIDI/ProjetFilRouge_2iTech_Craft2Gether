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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static fr.crafttogether.models.Bloc.BLOCK_TYPE.MANUFACTURE;
import static fr.crafttogether.models.Bloc.BLOCK_TYPE.NATUREL;

@SpringBootApplication
public class Craft2GetherApplication implements ApplicationRunner {
    private final BlocRepository blocRepository;
    private final ListeRepository listeRepository;
    private final UserRepository userRepository;
    private final RecetteRepository recetteRepository;
    @Value("${frontend.url}")
    private String frontendUrl;

    public Craft2GetherApplication(BlocRepository blocRepository, ListeRepository listeRepository, UserRepository userRepository, RecetteRepository recetteRepository) {
        this.blocRepository = blocRepository;
        this.listeRepository = listeRepository;
        this.userRepository = userRepository;
        this.recetteRepository = recetteRepository;
    }

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

       /* Bloc chene = Bloc.builder().nom("chene").biomeOrigine("all").outilNecessaire("hache_bois").type(NATUREL).build();
        Bloc plancheCheneBloc = Bloc.builder().nom("planche de chene").biomeOrigine("all").outilNecessaire("hache_bois").type(MANUFACTURE).build();
        Map<Integer, Bloc> quantiteChene = new HashMap<Integer, Bloc>();
        quantiteChene.put(1, chene);

        Bloc fer = Bloc.builder().nom("minerai de fer").biomeOrigine("all").outilNecessaire("pioche").type(NATUREL).build();
        Bloc lingoFerBloc = Bloc.builder().nom("lingot de fer").biomeOrigine("all").outilNecessaire("pioche").type(MANUFACTURE).build();
        Map<Integer, Bloc> quantiteLingot = new HashMap<Integer, Bloc>();
        quantiteLingot.put(1, lingoFerBloc);

        Bloc epee = Bloc.builder().nom("minerai de fer").biomeOrigine("all").outilNecessaire("pioche").type(MANUFACTURE).build();
        Map<Integer, Bloc> quantiteLingotpourepee = new HashMap<Integer, Bloc>();
        quantiteLingot.put(3, lingoFerBloc);

        Recette plancheCheneRecette = Recette.builder().nom("Planche de chene").ingredients(quantiteChene).resultat(plancheCheneBloc).build();
        Recette lingotFerRecette = Recette.builder().nom("lingot de fer").ingredients(quantiteLingot).resultat(lingoFerBloc).build();
        Recette epeeRecette = Recette.builder().nom("Epee en fer").ingredients(quantiteLingotpourepee).resultat(epee).build();

        blocRepository.save(chene);
        blocRepository.save(plancheCheneBloc);
        blocRepository.save(fer);
        blocRepository.save(lingoFerBloc);
        blocRepository.save(epee);

        recetteRepository.save(plancheCheneRecette);
        recetteRepository.save(lingotFerRecette);
        recetteRepository.save(epeeRecette);*/
    }
}
