package fr.crafttogether.controllers;

import fr.crafttogether.exceptions.BadRequestException;
import fr.crafttogether.exceptions.NotFoundException;
import fr.crafttogether.models.Liste;
import fr.crafttogether.services.ListeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin //authorise le front sur le port 5500 de live server
@RequestMapping("/listes") //Route general
@RestController //Controller rest qui ne retourne pas de vue
@AllArgsConstructor //Remplace l'autowired recommandé par spring
@NoArgsConstructor
public class ListeController {
    private ListeService listeService;

    // GET
    @GetMapping
    public List<Liste> getListes() {
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

    // GET BY NOM
    @GetMapping("{titre}")
    public Liste findByTitre(@PathVariable String titre) {
        Liste liste = listeService.findByTitre(titre);
        if (liste == null) {
            throw new NotFoundException("La liste recherchée n'existe pas");
        }
        return liste;
    }

    // POST : SAVE
    @PostMapping()
    public Liste saveListe(@RequestBody Liste liste) {
        if (liste.getId() != 0)
            throw new BadRequestException("id needs to be 0");
        return listeService.save(liste);
    }

    // POST : UPDATE
    @PutMapping("{id}")
    public Liste updateListe(@PathVariable long id, @Valid @RequestBody Liste liste){
        if(liste.getId() != id)
            throw new BadRequestException("ids in url and object do no match");
        return listeService.update(liste);
    };

    // DELETE
    @DeleteMapping("/delete/{id}")
    public void deleteListe(@PathVariable int id) {
        Liste list = listeService.findById(id);
        if (list == null) {
            throw new NotFoundException("La liste recherchée n'existe pas");
        }
        listeService.deleteById(id);
    }
}
