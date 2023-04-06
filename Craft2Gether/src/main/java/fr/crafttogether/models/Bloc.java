package fr.crafttogether.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Collection;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Bloc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private String outilNecessaire;

    private BLOCK_TYPE type;

    @OneToMany(mappedBy="resultat")
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

