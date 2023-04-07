package fr.crafttogether.controllers;

import fr.crafttogether.exceptions.BadRequestException;
import fr.crafttogether.exceptions.NotFoundException;
import fr.crafttogether.models.Liste;
import fr.crafttogether.models.User;
import fr.crafttogether.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RequestMapping("/users") //Route general
@RestController //Controller rest qui ne retourne pas de vue
@AllArgsConstructor //Remplace l'autowired recommandé par spring
@NoArgsConstructor
public class UserController {

    private UserService userService;

    // GET
    @GetMapping
    public Collection<User> getUsers() {
        return userService.findAll();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        User user = userService.findById(id);
        if (user == null) {
            throw new NotFoundException("Le user recherché n'existe pas");
        }
        return user;
    }

    // GET BY NOM
    @GetMapping("{nom}")
    public User getUserByName(@PathVariable String nom) {
        User user = userService.findByUsername(nom);
        if (user == null) {
            throw new NotFoundException("Le user recherché n'existe pas");
        }
        return user;
    }

    // GET LISTS FROM USER
    @GetMapping("/{id}/listes")
    public Collection<Liste> getListes(@PathVariable int id){
        User user = userService.findById(id);
        if (user == null) {
            throw new NotFoundException("Le user recherché n'existe pas");
        }
        Collection<Liste> listes = new ArrayList<Liste>();
        listes.addAll(user.getListeIParticipate());
        listes.addAll(user.getListesICreated());
        return listes;
    }

    // POST : SAVE
    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED) //Permet de changer le code serveur
    public User saveOrUpdateUser(@Valid @RequestBody User user) {
        if (user.getId() != 0)
            throw new BadRequestException("id needs to be 0");
        return userService.save(user);
    }

    // POST : UPDATE
    @PutMapping("{id}")
    public User updateUser(@PathVariable long id, @Valid @RequestBody User user){
        if(user.getId() != id)
            throw new BadRequestException("ids in url and object do no match");
        return userService.update(user);
    };

    // DELETE
    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable int id) {
        User user = userService.findById(id);
        if (user == null) {
            throw new NotFoundException("Le user recherché n'existe pas");
        }
        userService.deleteById(id);
    }
}
