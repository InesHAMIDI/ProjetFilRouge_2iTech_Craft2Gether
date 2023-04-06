package fr.crafttogether.controllers;

import fr.crafttogether.exceptions.BadRequestException;
import fr.crafttogether.exceptions.NotFoundException;
import fr.crafttogether.models.Bloc;
import fr.crafttogether.models.Recette;
import fr.crafttogether.services.RecetteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin //authorise le front sur le port 5500 de live server
@RequestMapping("/recettes") //Route general
@RestController //Controller rest qui ne retourne pas de vue
@AllArgsConstructor //Remplace l'autowired recommandé par spring
@NoArgsConstructor
public class RecetteController {

    private RecetteService recetteService;

    @GetMapping
    public List<Recette> getRecettes() {
        return recetteService.findAll();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public Recette getRecetteById(@PathVariable int id) {
        Recette recette = recetteService.findById(id);
        if (recette == null) {
            throw new NotFoundException("La recette recherchée n'existe pas");
        }
        return recette;
    }

    // GET BY NOM
    @GetMapping("{nom}")
    public Recette getRecetteByName(@PathVariable String nom) {
        Recette recette = recetteService.findByNom(nom);
        if (recette == null) {
            throw new NotFoundException("La recette recherchée n'existe pas");
        }
        return recette;
    }


    // POST : SAVE
    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED) //Permet de changer le code serveur
    public Recette saveOrUpdateRecette(@RequestBody Recette recette) {
        if (recette.getId() != 0)
            throw new BadRequestException("id needs to be 0");
        return recetteService.save(recette);
    }

    // POST : UPDATE
    @PutMapping("{id}")
    public Recette updateRecette(@PathVariable long id, @Valid @RequestBody Recette recette){
        if(recette.getId() != id)
            throw new BadRequestException("ids in url and object do no match");
        return recetteService.update(recette);
    };

    // DELETE
    @DeleteMapping("/delete/{id}")
    public void deleteRecette(@PathVariable int id) {
        Recette recette = recetteService.findById(id);
        if (recette == null) {
            throw new NotFoundException("La recette recherchée n'existe pas");
        }
        recetteService.deleteById(id);
    }
}
