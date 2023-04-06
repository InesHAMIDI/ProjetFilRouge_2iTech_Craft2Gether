package fr.crafttogether.models;

import jakarta.persistence.*;
import lombok.*;
import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Bloc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    private String nom;
    private String outilNecessaire;

    private BLOCK_TYPE type;

    @ManyToMany
    private Collection<Recette> recettesWhereImComponent;

    @OneToMany(mappedBy="resultat")
    private Collection<Recette> recettesToMakeMe;
    public enum BLOCK_TYPE
    {
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

