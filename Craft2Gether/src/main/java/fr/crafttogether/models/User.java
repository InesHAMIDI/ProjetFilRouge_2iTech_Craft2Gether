package fr.crafttogether.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Table(name = "MyUser")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String username;

    String password;

    private UserRole role;

    @OneToMany(mappedBy = "createur")
    private List<Liste> listesICreated;

    @ManyToMany(mappedBy = "collaborateurs")
    private List<Liste> listeIParticipate;

}
