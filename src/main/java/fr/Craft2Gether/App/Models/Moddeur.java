package fr.Craft2Gether.App.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
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
public class Moddeur extends User{

    @OneToMany
    private Set<Recette> recipeCreated;

    @OneToMany
    private Set<Bloc> blocCreated;
}
