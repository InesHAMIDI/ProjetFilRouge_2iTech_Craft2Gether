package fr.crafttogether.controllers;

import fr.crafttogether.exceptions.NotFoundException;
import fr.crafttogether.models.Recette;
import fr.crafttogether.services.RecetteService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RequestMapping("/recettes") //Route general
@RestController //Controller rest qui ne retourne pas de vue
@AllArgsConstructor //Remplace l'autowired recommandé par spring
public class RecetteController {
    private RecetteService recetteService;

    @GetMapping
    public Collection<Recette> getRecettes() {
        return recetteService.findAll();
    }

    // GET BY ID
    @GetMapping()
    public Recette getRecetteById(@RequestParam int id) {
        Recette recette = recetteService.findById(id);
        if (recette == null) {
            throw new NotFoundException("La recette recherchée n'existe pas");
        }
        return recette;
    }

    // GET BY NOM
    @GetMapping()
    public Recette getRecetteByName(@RequestParam String nom) {
        Recette recette = recetteService.findByNom(nom);
        if (recette == null) {
            throw new NotFoundException("La recette recherchée n'existe pas");
        }
        return recette;
    }

    // On ne prévoit pas le reste car personne ne peut modifier la base de données des blocs / recettes
}
