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
@RequestMapping("/lists")
@Slf4j
@AllArgsConstructor //remplace le AutoWired pour injecter les dépendances
public class ListController {

	//remplace l'appel aux repository
		private GenericService<fr.crafttogether.model.List, Integer> listService;
		
		@GetMapping()
		@ResponseBody
		public List<fr.crafttogether.model.List> getLists(){
			log.info("Liste des lists consultée");
				return listService.findAll();
			}
		
		@GetMapping("/{id}")
		@ResponseBody
		public ResponseEntity<fr.crafttogether.model.List> getList(@PathVariable int id) {
			var list = listService.findById(id);
			if(list == null) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "la recette recherchée n'existe pas");
			}
			log.info("liste recherchée avec id {}", id);
			return new ResponseEntity<fr.crafttogether.model.List>(list, HttpStatus.OK);
		}
		
		@PostMapping()
		@ResponseBody
		@ResponseStatus(code = HttpStatus.CREATED)
		public fr.crafttogether.model.List createList(@RequestBody fr.crafttogether.model.List list) {
			log.info("List added in BD");
			return listService.save(list);
		}

		@DeleteMapping("/{id}")
		@ResponseBody
		public ResponseEntity<Boolean> deleteList(@PathVariable int id) {
			if(listService.findById(id) == null) {
				log.error("List {} à supprimer introuvable", id);
				return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
			}
			listService.deleteById(id);
			log.info("List deleted {}", id);
		
			return new ResponseEntity<Boolean>(true, HttpStatus.NO_CONTENT);
		}
		
		@PutMapping()
		@ResponseBody
		public ResponseEntity<fr.crafttogether.model.List> modifList(@PathVariable int id,
									  @RequestBody fr.crafttogether.model.List list) {
			if(id != list.getNum()) {
				log.error("requete incohérente ! {} != {}", id, list.getNum());
				return new ResponseEntity<fr.crafttogether.model.List>(HttpStatus.BAD_REQUEST);
			}
			else if(listService.findById(id) == null) {
				log.error("liste introuvable ! {}", id);
				return new ResponseEntity<fr.crafttogether.model.List>(HttpStatus.NOT_FOUND);
			}
			log.info("liste modifiée {}", list);
			listService.save(list);
			return new ResponseEntity<fr.crafttogether.model.List>(HttpStatus.ACCEPTED);
		}
}
