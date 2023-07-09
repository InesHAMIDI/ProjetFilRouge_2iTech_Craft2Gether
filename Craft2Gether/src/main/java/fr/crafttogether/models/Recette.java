package fr.crafttogether.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Data
@SuperBuilder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Entity
public class Recette {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;
    @NonNull
    private String nom;

    @ManyToMany
    private List<Bloc> ingredients;

    @ManyToOne
    private Bloc resultat;

    private RECETTE_STATUS status;

    public enum RECETTE_STATUS{
        EN_COURS,
        FINISHED
    }
}
