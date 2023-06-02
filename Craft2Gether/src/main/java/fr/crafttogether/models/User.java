package fr.crafttogether.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Data
@SuperBuilder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
//@Table(name = "ctg_User")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @NonNull
    String username;
    @NotBlank
    @Length(min = 8)
    private String password;

    private UserRole role;


    @OneToMany(mappedBy = "createur")
    private List<Liste> listesICreated;

    @ManyToMany(mappedBy = "collaborateurs")
    private List<Liste> listeIParticipate;

}
