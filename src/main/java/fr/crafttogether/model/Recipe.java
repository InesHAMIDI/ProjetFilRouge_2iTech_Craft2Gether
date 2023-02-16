package fr.crafttogether.model;

import java.util.List;
import java.util.Map;

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
public class Recipe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer num;
	
	private String nom;

	@NonNull
	private String type;
	
	@NonNull
	private String category;
	
	@NonNull
	private List<Block> components;

	private int count;
	private String item;


	private String groupString;
	private String key;
	private String pattern;
	private Map<String, String> result;
}
