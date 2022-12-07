package fr.Craft2Gether.App.Models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Recette {

    @Id
    @EqualsAndHashCode.Exclude
    private long id;

    private String name;

    @ManyToMany
    private Set<Bloc> components;

    @ManyToOne
    private Bloc result;

}
