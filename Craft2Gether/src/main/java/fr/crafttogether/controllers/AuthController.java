package fr.crafttogether.controllers;

import jakarta.annotation.security.PermitAll;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth") //Route general
@CrossOrigin
@RestController //Controller rest qui ne retourne pas de vue
@AllArgsConstructor //Remplace l'autowired recommandé par spring@NoArgsConstructor
@PermitAll()
public class AuthController {

    // POST
    @PermitAll()
    @PostMapping
    public String authenticate(@RequestBody String values){
        return values;
    };

}
