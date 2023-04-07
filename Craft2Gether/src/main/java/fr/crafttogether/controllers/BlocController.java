package fr.crafttogether.controllers;

import fr.crafttogether.exceptions.NotFoundException;
import fr.crafttogether.models.Bloc;
import fr.crafttogether.services.BlocService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RequestMapping("/blocs") //Route general
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
            throw new NotFoundException("Le bloc recherchée n'existe pas");
        }
        return bloc;
    }

    // GET BY NOM
    @GetMapping("/{nom}")
    public Bloc getBlocByNom(@PathVariable String nom) {
        Bloc bloc = blocService.findByNom(nom);
        if (bloc == null) {
            throw new NotFoundException("Le bloc recherchée n'existe pas");
        }
        return bloc;
    }

    // On ne prévoit pas le reste car personne ne peut modifier la base de données des blocs / recettes

}
