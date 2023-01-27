package fr.crafttogether.service;

import fr.crafttogether.dao.BlockRepository;
import fr.crafttogether.dao.ListRepository;
import fr.crafttogether.dao.RecipeRepository;
import fr.crafttogether.model.List;

public class ListService  implements GenericService<List, Integer> {

	ListRepository listRepository;
	BlockRepository blockRepository;
	RecipeRepository recipeRepository;
	
	@Override
	public java.util.List<List> findAll() {
		return listRepository.findAll();
	}

	@Override
	public List findById(Integer id) {
		return listRepository.findById(id).orElse(null);
	}

	@Override
	public List save(List elt) {
		blockRepository.saveAll(elt.getBlocks());
		recipeRepository.saveAll(elt.getRecipes());
		return listRepository.save(elt);
	}

	@Override
	public void deleteById(Integer id) {
		listRepository.deleteById(id);
	}

	@Override
	public List update(List elt) {
		return listRepository.save(elt);
	}

}
