package fr.crafttogether;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fr.crafttogether.model.User;
import fr.crafttogether.service.UserService;

@SpringBootApplication
public class ProjetFilRouge_2iTech_Craft2GetherApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetFilRouge_2iTech_Craft2GetherApplication.class, args);
	}

	@Bean
	CommandLineRunner start(UserService userService) {
		return args->{
		userService.save(new User());
		};
	}
}
