package fr.crafttogether.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.HashMap;
import java.util.List;

@Data
@SuperBuilder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Table(name = "Liste")
@Entity
public class Liste {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    private String titre;

    @ManyToMany(cascade = {CascadeType.PERSIST})
    @JsonIgnore
    private List<Recette> recettes;

    @ManyToMany(cascade = {CascadeType.PERSIST})
    @JsonIgnore
    private HashMap<Integer, Bloc> blocs;//On a quantité-bloc

    @ManyToMany
    @JsonIgnore
    private List<User> collaborateurs;

    @ManyToOne
    @JsonIgnore
    private User createur;

    private STATUS status;

    public enum STATUS{
        EN_COURS,
        FINISHED
    }
}
