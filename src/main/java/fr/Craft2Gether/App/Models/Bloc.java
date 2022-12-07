package fr.Craft2Gether.App.Models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Bloc {

    @Id
    @EqualsAndHashCode.Exclude
    private Long id;

    private String name;
    private String spawnLocation;

    private BLOC_TYPES type;
    public enum BLOC_TYPES {
        BLOC_NATUREL,
        VEGETATION,
        MINERAI,
        ELEMENT_MECANIQUE,
        BLOC_MANUFACTURE,
        LIQUIDE,
        BLOC_COLORE,
        OBJET,
        NETHER;
    }


}
