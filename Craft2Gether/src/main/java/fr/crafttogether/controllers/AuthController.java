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
public class AuthController {
    private UserService userService;

    // POST
    @PermitAll()
    @PostMapping
    public User authenticate(@RequestBody User user){
        User u = userService.findByUsername(user.getUsername());
        if (u != null){
            return u;
        }
        else{
            throw new NotFoundException("Cet utilisateur n'existe pas");
        }
    };

}
