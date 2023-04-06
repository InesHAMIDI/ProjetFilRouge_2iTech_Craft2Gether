package fr.crafttogether.controllers;

import fr.crafttogether.models.User;
import fr.crafttogether.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin //authorise le front sur le port 5500 de live server
@RequestMapping("/users") //Route general
@RestController //Controller rest qui ne retourne pas de vue
@AllArgsConstructor //Remplace l'autowired recommandé par spring
public class UserController {

    private UserService userService;

    // GET
    @GetMapping
    public List<User> getUsers() {
        return userService.findAll();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        User user = userService.findById(id);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Le user recherché n'existe pas");
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // GET BY NOM
    @GetMapping("{nom}")
    public ResponseEntity<User> getUserByName(@PathVariable String nom) {
        User user = userService.findByNom(nom);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Le user recherché n'existe pas");
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    // POST : SAVE OR UPDATE
    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED) //Permet de changer le code serveur
    public User saveOrUpdateUser(@RequestBody User user) {
        return userService.save(user);
    }

    // DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable int id) {
        User user = userService.findById(id);
        if (user == null) {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
        userService.deleteById(id);
        return new ResponseEntity<>(true, HttpStatus.NO_CONTENT);
    }

    //PUT
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable int id) {
        if (id == user.getId()) {
            if (userService.findById(id) == null) {
                return new ResponseEntity<>(user, HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userService.save(user), HttpStatus.ACCEPTED);

    }
}
