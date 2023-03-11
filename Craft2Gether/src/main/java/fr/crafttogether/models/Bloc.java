package fr.crafttogether.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bloc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NonNull
    private String nom;
    private String outilNecessaire;

    @ManyToOne()
    private Recette recette;
    private enum types {
        NATUREL,
        MANUFACTURE,
        DECORATIF,
        OBJET,
        COLORE,
        MECANIQUE,
        VEGETATION,
        MINERAI,
        LIQUIDE,
        NETHER,
        ENDER
    }
}

