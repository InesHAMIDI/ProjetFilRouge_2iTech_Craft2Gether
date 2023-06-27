package fr.crafttogether.controllers;

import fr.crafttogether.exceptions.NotFoundException;
import fr.crafttogether.models.Bloc;
import fr.crafttogether.services.BlocService;
import jakarta.annotation.security.RolesAllowed;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RequestMapping("/blocs") //Route general
@CrossOrigin
@RestController //Controller rest qui ne retourne pas de vue
@AllArgsConstructor //Remplace l'autowired recommandé par spring@NoArgsConstructor
public class BlocController {
    private BlocService blocService;

    // GET
    @GetMapping
    public Collection<Bloc> getBlocs() {
        return blocService.findAll();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public Bloc getBlocById(@PathVariable int id) {
        Bloc bloc = blocService.findById(id);
        if (bloc == null) {
            throw new NotFoundException("Le bloc recherché n'existe pas");
        }
        return bloc;
    }

    // GET BY NOM
    /*@GetMapping("/{nom}")
    public Bloc getBlocByNom(@PathVariable String nom) {
        Bloc bloc = blocService.findByNom(nom);
        if (bloc == null) {
            throw new NotFoundException("Le bloc recherché n'existe pas");
        }
        return bloc;
    }*/

    // On ne prévoit pas le reste car personne ne peut modifier la base de données des blocs / recettes

}
