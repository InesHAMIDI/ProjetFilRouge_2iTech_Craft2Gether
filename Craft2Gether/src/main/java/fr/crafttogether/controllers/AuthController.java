package fr.crafttogether.controllers;

import fr.crafttogether.exceptions.NotFoundException;
import fr.crafttogether.models.User;
import fr.crafttogether.services.UserService;
import jakarta.annotation.security.PermitAll;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth") //Route general
@CrossOrigin
@RestController //Controller rest qui ne retourne pas de vue
@AllArgsConstructor //Remplace l'autowired recommandé par spring@NoArgsConstructor
@PermitAll()
public class AuthController {
    private UserService userService;

    // POST
    @PermitAll()
    @PostMapping
    public User authenticate(@RequestParam String username, @RequestParam String password){
        User user = userService.findByUsername(username);
        if (user != null){
            return user;
        }
        else{
            throw new NotFoundException("Cet utilisateur n'existe pas");
        }
    };

}
