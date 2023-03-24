package fr.crafttogether.controllers;

import fr.crafttogether.models.Bloc;
import fr.crafttogether.services.BlocService;
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
public class BlocController {
    BlocService blocService;

    // GET
    @GetMapping
    public List<Bloc> getBlocs() {
        return blocService.findAll();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Bloc> getBlocById(@PathVariable int id) {
        Bloc bloc = blocService.findById(id);
        if (bloc == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La liste recherchée n'existe pas");
        }
        return new ResponseEntity<>(bloc, HttpStatus.OK);
    }

    // POST : SAVE OR UPDATE
    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED) //Permet de changer le code serveur
    public Bloc saveOrUpdateBloc(@RequestBody Bloc bloc) {
        return blocService.save(bloc);
    }

    // DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteBloc(@PathVariable int id) {
        Bloc bloc = blocService.findById(id);
        if (bloc == null) {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
        blocService.deleteById(id);
        return new ResponseEntity<>(true, HttpStatus.NO_CONTENT);
    }

    //PUT
    @PutMapping("/{id}")
    public ResponseEntity<Bloc> updateListe(@RequestBody Bloc bloc, @PathVariable int id) {
        if (id == bloc.getId()) {
            if (blocService.findById(id) == null) {
                return new ResponseEntity<>(bloc, HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(bloc, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(blocService.save(bloc), HttpStatus.ACCEPTED);

    }
}
