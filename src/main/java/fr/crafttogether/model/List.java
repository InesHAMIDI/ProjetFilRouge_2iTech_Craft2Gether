package fr.crafttogether.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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
public class List {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer num;
	@NonNull
	private String nom;
	
	@NonNull
	private User creator;
	
	@ManyToMany(mappedBy = "lists")
	@JsonIgnoreProperties
	private java.util.List<User> collaborators;
	
	private java.util.List<Block> blocks;
	private java.util.List<Recipe> recipes;
}
