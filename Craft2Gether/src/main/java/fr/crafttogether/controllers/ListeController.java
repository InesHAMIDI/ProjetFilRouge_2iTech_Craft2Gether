package fr.crafttogether.controllers;

import fr.crafttogether.models.Liste;
import fr.crafttogether.services.ListeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin //authorise le front sur le port 5500 de live server
@RequestMapping("/listes") //Route general
@RestController //Controller rest qui ne retourne pas de vue
@AllArgsConstructor //Remplace l'autowired recommandé par spring
public class ListeController {
    private ListeService listeService;

    // GET
    @GetMapping
    public List<Liste> getListes() {
        return listeService.findAll();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Liste> getListeById(long id) {
        Liste list = listeService.findById(id);
        if (list == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La liste recherchée n'existe pas");
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // POST : SAVE OR UPDATE
    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED) //Permet de changer le code serveur
    public Liste saveOrUpdateListe(@RequestBody Liste liste) {
        return listeService.save(liste);
    }

    // DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteListe(@PathVariable long id) {
        Liste list = listeService.findById(id);
        if (list == null) {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
        listeService.deleteById(id);
        return new ResponseEntity<>(true, HttpStatus.NO_CONTENT);
    }

    //PUT
    @PutMapping("/{id}")
    public ResponseEntity<Liste> updateListe(@RequestBody Liste liste, @PathVariable long id) {
        if (id != liste.getId()) {
            return new ResponseEntity<>(liste, HttpStatus.BAD_REQUEST);
        } else if (listeService.findById(id) == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(listeService.save(liste), HttpStatus.ACCEPTED);

    }
}
