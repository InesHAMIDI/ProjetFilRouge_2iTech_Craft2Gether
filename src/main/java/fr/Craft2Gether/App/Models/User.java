package fr.Craft2Gether.App.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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
public class User {

    @Id
    @EqualsAndHashCode.Exclude
    private long id;

    private String pseudo;
    private String password;

    @ManyToMany(mappedBy = "collaborators")
    private Set<Liste> myCollabLists;

    @OneToMany(mappedBy = "creator")
    private Set<Liste> myLists;
}
