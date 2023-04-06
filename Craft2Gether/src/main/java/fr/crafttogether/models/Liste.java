package fr.crafttogether.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Liste {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String titre;

    @ManyToMany
    private Collection<Recette> recettes;

    @ManyToMany
    private HashMap<Integer, Bloc> blocs;//On a quantité-bloc

    @ManyToMany
    private Collection<User> collaborateurs;

    @ManyToOne
    private User createur;

}
