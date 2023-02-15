package fr.crafttogether.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
	private String type;
	@NonNull
	private String category;
	
	@ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE})
	@JsonIgnoreProperties(value = "recipesImAComponent")
	private List<Recipe> recipesImAComponent;
	
	@ManyToOne
	private List<Recipe> recipesToMakeMe;
	
	@OneToMany(mappedBy = "creationsBL")
	private Modder creator;
}
