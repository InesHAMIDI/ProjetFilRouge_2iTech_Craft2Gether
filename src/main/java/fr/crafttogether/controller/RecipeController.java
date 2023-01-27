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
import org.springframework.web.server.ResponseStatusException;

import fr.crafttogether.model.Recipe;
import fr.crafttogether.service.GenericService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/recipes")
@Slf4j
@AllArgsConstructor //remplace le AutoWired pour injecter les dépendances
public class RecipeController {

	//remplace l'appel aux repository
	private GenericService<Recipe, Integer> recipeService;
	
	@GetMapping()
	@ResponseBody
	public List<Recipe> getRecipes(){
		log.info("Liste users consultée");
			return recipeService.findAll();
		}
	
	@GetMapping("/{id}")
	@ResponseBody
	public ResponseEntity<Recipe> getRecipe(@PathVariable int id) {
		var recipe = recipeService.findById(id);
		if(recipe == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "la recette recherchée n'existe pas");
		}
		log.info("recette recherchée avec id {}", id);
		return new ResponseEntity<Recipe>(recipe, HttpStatus.OK);
	}
	
	@PostMapping()
	@ResponseBody
	@ResponseStatus(code = HttpStatus.CREATED)
	public Recipe createPersonne(@RequestBody Recipe recipe) {
		log.info("Personne added in BD");
		return recipeService.save(recipe);
	}

	@DeleteMapping("/{id}")
	@ResponseBody
	public ResponseEntity<Boolean> deletePersonne(@PathVariable int id) {
		if(recipeService.findById(id) == null) {
			log.error("Recipe {} à supprimer introuvable",id);
			return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
		}
		recipeService.deleteById(id);
		log.info("Recipe deleted {}", id);
	
		return new ResponseEntity<Boolean>(true, HttpStatus.NO_CONTENT);
	}
	
	@PutMapping()
	@ResponseBody
	public ResponseEntity<Recipe> modifPersonne(@PathVariable int id,
								  @RequestBody Recipe recipe) {
		if(id != recipe.getNum()) {
			log.error("requete incohérente ! {} != {}", id, recipe.getNum());
			return new ResponseEntity<Recipe>(HttpStatus.BAD_REQUEST);
		}
		else if(recipeService.findById(id) == null) {
			log.error("personne introuvable ! {}", id);
			return new ResponseEntity<Recipe>(HttpStatus.NOT_FOUND);
		}
		log.info("personne modifiée {}", recipe);
		recipeService.save(recipe);
		return new ResponseEntity<Recipe>(HttpStatus.ACCEPTED);
	}
}
