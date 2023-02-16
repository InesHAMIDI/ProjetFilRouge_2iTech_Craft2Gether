package fr.crafttogether.model;

import java.util.List;

import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class Modder extends User {

	//@OneToMany
	private List<Recipe> creationsRE;
	//@OneToMany(mappedBy = "creator")
	private List<Block> creationsBL;
}
