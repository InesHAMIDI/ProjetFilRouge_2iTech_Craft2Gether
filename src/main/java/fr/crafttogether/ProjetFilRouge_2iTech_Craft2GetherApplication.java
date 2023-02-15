package fr.crafttogether;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.crafttogether.model.Recipe;
import fr.crafttogether.service.RecipeService;

@SpringBootApplication
public class ProjetFilRouge_2iTech_Craft2GetherApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetFilRouge_2iTech_Craft2GetherApplication.class, args);
	}

	@Bean
	CommandLineRunner start(RecipeService recipeService) {
		return args -> {
			// read json and write to db
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<Recipe> typeReference = new TypeReference<Recipe>() {
			};
			InputStream inputStream = TypeReference.class.getResourceAsStream("/resources/MinecraftJsonData/andesite.json");
			try {
				Recipe res = mapper.readValue(inputStream,typeReference);
				
			} catch (IOException e) {
				System.out.println("Unable to save recipe: " + e.getMessage());
			}
		};
	}
}
