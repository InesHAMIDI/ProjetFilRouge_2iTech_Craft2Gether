package fr.crafttogether.controllers;

import fr.crafttogether.exceptions.BadRequestException;
import fr.crafttogether.exceptions.NotFoundException;
import fr.crafttogether.models.Liste;
import fr.crafttogether.models.Recette;
import fr.crafttogether.services.ListeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RequestMapping("/listes") //Route general
@RestController //Controller rest qui ne retourne pas de vue
@AllArgsConstructor //Remplace l'autowired recommandé par spring
public class ListeController {
    private ListeService listeService;

    // GET
    @GetMapping
    public Collection<Liste> getListes() {
        return listeService.findAll();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public Liste getListeById(@PathVariable int id) {
        Liste list = listeService.findById(id);
        if (list == null) {
            throw new NotFoundException("La liste recherchée n'existe pas");
        }
        return list;
    }

    // GET LISTE CONTENT RECETTES
    @GetMapping("/{id}/recettes")
    public Collection<Recette> getRecettesFromListe(@PathVariable int id) {
        Liste list = listeService.findById(id);
        if (list == null) {
            throw new NotFoundException("La liste recherchée n'existe pas");
        }
        return list.getRecettes();
    }

    // POST : SAVE
    @PostMapping()
    public Liste saveListe(@Valid @RequestBody Liste liste) {
        if (liste.getId() != 0)
            throw new BadRequestException("id needs to be 0");
        return listeService.save(liste);
    }

    // POST : UPDATE
    @PutMapping("/{id}")
    public Liste updateListe(@PathVariable long id, @Valid @RequestBody Liste liste){
        if(liste.getId() != id)
            throw new BadRequestException("ids in url and object do no match");
        return listeService.update(liste);
    };

    // DELETE
    @DeleteMapping("    /{id}")
    public void deleteListe(@PathVariable int id) {
        Liste list = listeService.findById(id);
        if (list == null) {
            throw new NotFoundException("La liste recherchée n'existe pas");
        }
        listeService.deleteById(id);
    }
}
