package fr.crafttogether.controllers;

import fr.crafttogether.services.UserService;
import jakarta.annotation.security.PermitAll;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public void authenticate(){
    };

}
