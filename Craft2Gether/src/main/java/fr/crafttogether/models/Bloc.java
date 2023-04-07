package fr.crafttogether.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Entity
public class Bloc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private String biomeOrigine;
    private String outilNecessaire;

    private BLOCK_TYPE type;

    @OneToMany(mappedBy= "resultat")
    @JsonIgnore
    private List<Recette> recettesToMakeMe;
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

