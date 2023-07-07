package fr.crafttogether.controllers;

import fr.crafttogether.models.User;
import fr.crafttogether.services.UserService;
import jakarta.annotation.security.PermitAll;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth") //Route general
@CrossOrigin("http://localhost:8080")
@RestController //Controller rest qui ne retourne pas de vue
@AllArgsConstructor //Remplace l'autowired recommandé par spring@NoArgsConstructor
@PermitAll()
public class AuthController {
    private UserService userService;
    private PasswordEncoder passwordEncoder;
    // POST
    @PermitAll
    @PostMapping
    public User authenticate(@RequestParam String username, @RequestParam String password){
        User user = userService.findByUsername(username);
        if(!passwordEncoder.matches(password, user.getPassword()))
           throw new RuntimeException();
        return user;

    };

}
