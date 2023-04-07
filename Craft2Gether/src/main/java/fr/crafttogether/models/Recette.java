package fr.crafttogether.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Map;

@Data
@SuperBuilder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Entity
public class Recette {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;

    @ManyToMany(cascade = {CascadeType.PERSIST})
    private Map<Integer, Bloc> ingredients; // quantité-bloc

    @ManyToOne(cascade = {CascadeType.PERSIST})
    private Bloc resultat; // quantité-bloc
}
