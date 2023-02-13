package fr.crafttogether.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import fr.crafttogether.model.User;
import fr.crafttogether.service.GenericService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/users")
@Slf4j
@AllArgsConstructor //remplace le AutoWired pour injecter les dépendances
public class UserController {


	//remplace l'appel aux repository
		private GenericService<User, Integer> userService;
		
		@GetMapping()
		@ResponseBody
		public List<User> getRecipes(){
			log.info("Liste users consultée");
				return userService.findAll();
		}
		
		@GetMapping("/{id:\\d+}")
		@ResponseBody
		public ResponseEntity<User> getRecipe(@PathVariable int id) {
			var user = userService.findById(id);
			if(user == null) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "le user recherché n'existe pas");
			}
			log.info("user recherchée avec id {}", id);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
		
		@PostMapping()
		@ResponseBody
		@ResponseStatus(code = HttpStatus.CREATED)
		public User createUser(@RequestBody User user) {
			log.info("user added in BD");
			return userService.save(user);
		}

		@DeleteMapping("/{id:\\d+}")
		@ResponseBody
		public ResponseEntity<Boolean> deleteUser(@PathVariable int id) {
			if(userService.findById(id) == null) {
				log.error("user {} à supprimer introuvable",id);
				return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
			}
			userService.deleteById(id);
			log.info("user deleted {}", id);
		
			return new ResponseEntity<Boolean>(true, HttpStatus.NO_CONTENT);
		}
		
		@PutMapping()
		@ResponseBody
		public ResponseEntity<User> modifUser(@PathVariable int id,
									  @RequestBody User user) {
			if(id != user.getNum()) {
				log.error("requete incohérente ! {} != {}", id, user.getNum());
				return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
			}
			else if(userService.findById(id) == null) {
				log.error("user introuvable ! {}", id);
				return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
			}
			log.info("user modifié {}", user);
			userService.save(user);
			return new ResponseEntity<User>(HttpStatus.ACCEPTED);
		}
}
