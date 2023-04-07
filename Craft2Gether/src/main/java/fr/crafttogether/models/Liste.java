package fr.crafttogether.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import jakarta.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.List;

@Data
@SuperBuilder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Entity
public class Liste {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
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
