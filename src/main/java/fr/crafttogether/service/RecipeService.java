package fr.crafttogether.service;

import java.util.List;

import fr.crafttogether.dao.BlockRepository;
import fr.crafttogether.dao.RecipeRepository;
import fr.crafttogether.model.Recipe;

public class RecipeService implements GenericService<Recipe, Integer> {

	RecipeRepository recipeRepository;
	BlockRepository blockRepository;
	
	@Override
	public List<Recipe> findAll() {
		return recipeRepository.findAll();
	}

	@Override
	public Recipe findById(Integer id) {
		return recipeRepository.findById(id).orElse(null);
	}

	@Override
	public Recipe save(Recipe elt) {
		blockRepository.saveAll(elt.getComponents());
		return recipeRepository.save(elt);
	}

	@Override
	public void deleteById(Integer id) {
		recipeRepository.deleteById(id);
		
	}

	@Override
	public Recipe update(Recipe elt) {
		return recipeRepository.save(elt);
	}

}
