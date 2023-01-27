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

import fr.crafttogether.service.GenericService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@AllArgsConstructor //remplace le AutoWired pour injecter les dépendances
@Slf4j
public class GenericController<T> {

	//remplace l'appel aux repository
		private GenericService<T, Integer> genericService;
		
		@GetMapping()
		@ResponseBody
		public List<T> getTs(){
			log.info("Liste recipes consultée");
				return genericService.findAll();
			}
		
		@GetMapping("/{id}")
		@ResponseBody
		public ResponseEntity<T> getT(@PathVariable int id) {
			var recipe = genericService.findById(id);
			if(recipe == null) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "la recette recherchée n'existe pas");
			}
			log.info("recette recherchée avec id {}", id);
			return new ResponseEntity<T>(recipe, HttpStatus.OK);
		}
		
		@PostMapping()
		@ResponseBody
		@ResponseStatus(code = HttpStatus.CREATED)
		public T createT(@RequestBody T recipe) {
			log.info("T added in BD");
			return genericService.save(recipe);
		}

		@DeleteMapping("/{id}")
		@ResponseBody
		public ResponseEntity<Boolean> deleteT(@PathVariable int id) {
			if(genericService.findById(id) == null) {
				log.error("T {} à supprimer introuvable",id);
				return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
			}
			genericService.deleteById(id);
			log.info("T deleted {}", id);
		
			return new ResponseEntity<Boolean>(true, HttpStatus.NO_CONTENT);
		}
		
		@PutMapping()
		@ResponseBody
		public ResponseEntity<T> modifT(@PathVariable int id,
									  @RequestBody T recipe) {
			if(id != recipe.getNum()) {
				log.error("requete incohérente ! {} != {}", id, recipe.getNum());
				return new ResponseEntity<T>(HttpStatus.BAD_REQUEST);
			}
			else if(genericService.findById(id) == null) {
				log.error("recipe introuvable ! {}", id);
				return new ResponseEntity<T>(HttpStatus.NOT_FOUND);
			}
			log.info("recipe modifiée {}", recipe);
			genericService.save(recipe);
			return new ResponseEntity<T>(HttpStatus.ACCEPTED);
		}
}
