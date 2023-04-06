package fr.crafttogether.models;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@SuperBuilder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String username;

    String password;

    @OneToMany(mappedBy = "createur")
    private List<Liste> listesICreated;

    @ManyToMany(mappedBy = "collaborateurs")
    private List<Liste> listeIParticipate;

}
