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

        Bloc chene = Bloc.builder().nom("chene").biomeOrigine("all").outilNecessaire("hache_bois").type(NATUREL).build();
        Bloc plancheCheneBloc = Bloc.builder().nom("planche de chene").biomeOrigine("all").outilNecessaire("hache_bois").type(MANUFACTURE).build();

        Map<Integer, Bloc> quantiteChene = new HashMap<Integer, Bloc>();
        quantiteChene.put(1, chene);

        Recette plancheCheneRecette = Recette.builder().nom("Planche de chene").ingredients(quantiteChene).resultat(plancheCheneBloc).build();

        List<Recette> recettesList1 = new ArrayList<>();
        recettesList1.add(plancheCheneRecette);

        Liste liste1 = Liste.builder().titre("Planches à faire").recettes(recettesList1).status(Liste.STATUS.EN_COURS).build();
        Liste liste2 = Liste.builder().titre("EPEES POUR EQUIPE").status(Liste.STATUS.FINISHED).build();
        Liste liste3 = Liste.builder().titre("BOucliers").status(Liste.STATUS.EN_COURS).build();

        listeRepository.save(liste1);
        listeRepository.save(liste2);
        listeRepository.save(liste3);
        blocRepository.save(chene);
        blocRepository.save(plancheCheneBloc);
        recetteRepository.save(plancheCheneRecette);

    }
}
