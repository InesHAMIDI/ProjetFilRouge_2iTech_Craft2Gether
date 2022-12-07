package fr.Craft2Gether.App.Models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ManyToAny;

import java.util.Collection;
import java.util.Set;

@Entity
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Liste {

    @Id
    @EqualsAndHashCode.Exclude
    private long id;

    @ManyToMany(mappedBy = "myCollabLists")
    private Set<User> collaborators;

    @ManyToOne
    private User creator;

    @ManyToAny
    private Set<Bloc> blocs;

    @ManyToAny
    private Set<Recette> recipes;
}
