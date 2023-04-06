package fr.crafttogether.controllers;

import fr.crafttogether.exceptions.BadRequestException;
import fr.crafttogether.exceptions.NotFoundException;
import fr.crafttogether.models.Bloc;
import fr.crafttogether.services.BlocService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

//@CrossOrigin //authorise le front sur le port 5500 de live server
@RequestMapping("/blocs") //Route general
@RestController //Controller rest qui ne retourne pas de vue
@AllArgsConstructor //Remplace l'autowired recommandé par spring@NoArgsConstructor
public class BlocController {
    private BlocService blocService;

    // GET
    @GetMapping
    public List<Bloc> getBlocs() {
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

    // POST : SAVE
    @PostMapping()
    //@ResponseStatus(code = HttpStatus.CREATED) //Permet de changer le code serveur
    public Bloc saveBloc(@RequestBody Bloc bloc) {
        if (bloc.getId() != 0)
            throw new BadRequestException("id needs to be 0");
        return blocService.save(bloc);
    }

    // POST : UPDATE
    @PutMapping("{id}")
    public Bloc updateBloc(@PathVariable long id, @Valid @RequestBody Bloc bloc){
        if(bloc.getId() != id)
            throw new BadRequestException("ids in url and object do no match");
        return blocService.update(bloc);
    };

    // DELETE
    @DeleteMapping("/delete/{id}")
    public void deleteBloc(@PathVariable int id) {
        Bloc bloc = blocService.findById(id);
        if (bloc == null) {
            throw new NotFoundException("ids in url and object do no match");
        }
        blocService.deleteById(id);
    }
}
