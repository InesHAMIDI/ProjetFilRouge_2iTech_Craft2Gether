package fr.crafttogether.controllers;

import fr.crafttogether.exceptions.BadRequestException;
import fr.crafttogether.exceptions.NotFoundException;
import fr.crafttogether.models.Liste;
import fr.crafttogether.models.Recette;
import fr.crafttogether.services.ListeService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RequestMapping("/listes") //Route general
@RestController //Controller rest qui ne retourne pas de vue
@AllArgsConstructor //Remplace l'autowired recommandé par spring
@RolesAllowed({"PLAYER", "ADMIN"}) //faut avoir l'un ou l'autre, s'écrit aussi @Secured({"ROLE_ADMIN", "ROLE_USER"})
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
    @DeleteMapping("/{id}")
    public void deletebyId(@PathVariable int id) throws InterruptedException {
        listeService.deleteById(id);
    }
}
