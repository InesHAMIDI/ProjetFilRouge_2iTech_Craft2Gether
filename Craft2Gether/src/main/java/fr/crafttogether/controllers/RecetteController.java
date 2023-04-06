package fr.crafttogether.controllers;

import fr.crafttogether.models.Recette;
import fr.crafttogether.services.RecetteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin //authorise le front sur le port 5500 de live server
@RequestMapping("/recettes") //Route general
@RestController //Controller rest qui ne retourne pas de vue
@AllArgsConstructor //Remplace l'autowired recommandé par spring
public class RecetteController {

    private RecetteService recetteService;

    @GetMapping
    public List<Recette> getRecettes() {
        return recetteService.findAll();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Recette> getRecetteById(@PathVariable int id) {
        Recette recette = recetteService.findById(id);
        if (recette == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La recette recherchée n'existe pas");
        }
        return new ResponseEntity<>(recette, HttpStatus.OK);
    }

    // GET BY NOM
    @GetMapping("{nom}")
    public ResponseEntity<Recette> getRecetteByName(@PathVariable String nom) {
        Recette recette = recetteService.findByNom(nom);
        if (recette == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Le recette recherché n'existe pas");
        }
        return new ResponseEntity<>(recette, HttpStatus.OK);
    }


    // POST : SAVE OR UPDATE
    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED) //Permet de changer le code serveur
    public Recette saveOrUpdateRecette(@RequestBody Recette recette) {
        return recetteService.save(recette);
    }

    // DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteRecette(@PathVariable int id) {
        Recette recette = recetteService.findById(id);
        if (recette == null) {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
        recetteService.deleteById(id);
        return new ResponseEntity<>(true, HttpStatus.NO_CONTENT);
    }

    //PUT
    @PutMapping("/{id}")
    public ResponseEntity<Recette> updateRecette(@RequestBody Recette recette, @PathVariable int id) {
        if (id == recette.getId()) {
            if (recetteService.findById(id) == null) {
                return new ResponseEntity<>(recette, HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(recette, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(recetteService.save(recette), HttpStatus.ACCEPTED);

    }
}
