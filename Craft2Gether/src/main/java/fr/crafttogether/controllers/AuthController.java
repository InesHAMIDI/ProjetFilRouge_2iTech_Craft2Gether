package fr.crafttogether.controllers;

import fr.crafttogether.exceptions.NotFoundException;
import fr.crafttogether.models.User;
import fr.crafttogether.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth") //Route general
@CrossOrigin
@RestController //Controller rest qui ne retourne pas de vue
@AllArgsConstructor //Remplace l'autowired recommandé par spring@NoArgsConstructor
public class AuthController {
    private UserService userService;

    // POST
    @PostMapping
    public User authenticate(@RequestParam String username, @RequestParam String password){
        User user = userService.findByUsername(username);
        if (user != null && user.getPassword().equals(password)){
            return user;
        }
        else{
            throw new NotFoundException("Cet utilisateur n'existe pas");
        }
    };

}
