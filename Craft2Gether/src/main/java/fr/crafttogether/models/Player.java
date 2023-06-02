package fr.crafttogether.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Entity
public class Player extends User {

    @OneToMany(mappedBy="createur", cascade= CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<Liste> listes;

}
