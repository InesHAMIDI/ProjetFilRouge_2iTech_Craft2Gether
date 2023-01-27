package fr.crafttogether.controller;

import java.util.List;

import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.springframework.beans.factory.annotation.Autowired;
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
public abstract class GenericController<T> {

	//remplace l'appel aux repository
		private GenericService<T, Integer> genericService;
		
		@GetMapping()
		@ResponseBody
		public List<T> getTs(){
			log.info("Liste {} consultée", this.getClass());
				return genericService.findAll();
			}
		
		@GetMapping("/{id}")
		@ResponseBody
		public ResponseEntity<T> getT(@PathVariable int id) {
			var elt = genericService.findById(id);
			if(elt == null) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "l'élément recherché n'existe pas");
			}
			log.info("{} recherchée avec id {}",this.getClass(), id);
			return new ResponseEntity<T>(elt, HttpStatus.OK);
		}
		
		@PostMapping()
		@ResponseBody
		@ResponseStatus(code = HttpStatus.CREATED)
		public T createT(@RequestBody T elt) {
			log.info("{} added in BD", this.getClass());
			return genericService.save(elt);
		}

		@DeleteMapping("/{id}")
		@ResponseBody
		public ResponseEntity<Boolean> deleteT(@PathVariable int id) {
			if(genericService.findById(id) == null) {
				log.error("{} {} à supprimer introuvable",this.getClass(), id);
				return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
			}
			genericService.deleteById(id);
			log.info("{} deleted {}",this.getClass(), id);
		
			return new ResponseEntity<Boolean>(true, HttpStatus.NO_CONTENT);
		}
		
	
}
