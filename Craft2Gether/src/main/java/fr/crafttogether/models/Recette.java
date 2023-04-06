package fr.crafttogether.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recette {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;

    @ManyToMany(mappedBy="recettesWhereImComponent")
    private Map<Integer, Bloc> ingredients; // quantité-bloc

    @ManyToOne
    private Bloc resultat; // quantité-bloc
}
