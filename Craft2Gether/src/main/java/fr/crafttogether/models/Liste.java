package fr.crafttogether.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Liste {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String titre;

    @ManyToMany
    private List<Recette> recettes;

    @ManyToMany
    private HashMap<Integer, Bloc> blocs;//On a quantité-bloc

    @ManyToMany
    private List<User> collaborateurs;

    @ManyToOne
    private User createur;

}
