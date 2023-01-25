package fr.crafttogether.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Block {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NonNull
	private String spawnLocation;
	@NonNull
	private String nom;
	@NonNull
	private Type type;
	
	@ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE})
	@JsonIgnoreProperties(value = "recipes")
	private List<Recipe> recipes;
	
	private Modder creator;
}
