package fr.crafttogether.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

import java.util.Collection;

@Data
@SuperBuilder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Entity
public class Bloc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    private String nom;
    @NonNull
    private String biomeOrigine;
    @NonNull
    private String outilNecessaire;
    @NonNull
    private BLOCK_TYPE type;
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

