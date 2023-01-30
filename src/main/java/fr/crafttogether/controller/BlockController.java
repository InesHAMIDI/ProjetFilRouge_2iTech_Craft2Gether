package fr.crafttogether.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

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

import fr.crafttogether.model.Block;
import fr.crafttogether.model.Recipe;
import fr.crafttogether.service.GenericService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/blocks")
@Slf4j
@AllArgsConstructor //remplace le AutoWired pour injecter les dépendances
public class BlockController {

	//remplace l'appel aux repository
		private GenericService<Block, Integer> blockService;
		
		@GetMapping()
		@ResponseBody
		public List<Block> getRecipes(){
			log.info("Liste blocks consultée");
				return blockService.findAll();
		}
		
		@GetMapping("/{id}")
		@ResponseBody
		public ResponseEntity<Block> getRecipe(@PathVariable int id) {
			var block = blockService.findById(id);
			if(block == null) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "le block recherché n'existe pas");<>
			}
			log.info("block recherchée avec id {}", id);
			return new ResponseEntity<Block>(block, HttpStatus.OK);
		}
		
		@PostMapping()
		@ResponseBody
		@ResponseStatus(code = HttpStatus.CREATED)
		public Block createBlock(@RequestBody Block block) {
			log.info("block added in BD");
			return blockService.save(block);
		}

		@DeleteMapping("/{id}")
		@ResponseBody
		public ResponseEntity<Boolean> deleteBlock(@PathVariable int id) {
			if(blockService.findById(id) == null) {
				log.error("block {} à supprimer introuvable",id);
				return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
			}
			blockService.deleteById(id);
			log.info("block deleted {}", id);
		
			return new ResponseEntity<Boolean>(true, HttpStatus.NO_CONTENT);
		}
		
		@PutMapping()
		@ResponseBody
		public ResponseEntity<Block> modifBlock(@PathVariable int id,
									  @RequestBody Block block) {
			if(id != block.getId()) {
				log.error("requete incohérente ! {} != {}", id, block.getId());
				return new ResponseEntity<Block>(HttpStatus.BAD_REQUEST);
			}
			else if(blockService.findById(id) == null) {
				log.error("block introuvable ! {}", id);
				return new ResponseEntity<Block>(HttpStatus.NOT_FOUND);
			}
			log.info("block modifié {}", block);
			blockService.save(block);
			return new ResponseEntity<Block>(HttpStatus.ACCEPTED);
		}
}
