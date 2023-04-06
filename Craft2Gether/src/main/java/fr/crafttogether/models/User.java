package fr.crafttogether.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @NonNull
    String username;

    @NonNull
    String password;

    @OneToMany(mappedBy = "createur")
    private Collection<Liste> listesICreated;

    @ManyToMany(mappedBy = "collaborateurs")
    private Collection<Liste> listeIParticipate;

}
