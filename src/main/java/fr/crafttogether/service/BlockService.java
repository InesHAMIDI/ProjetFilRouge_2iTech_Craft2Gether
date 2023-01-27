package fr.crafttogether.service;

import java.util.List;

import fr.crafttogether.dao.BlockRepository;
import fr.crafttogether.dao.RecipeRepository;
import fr.crafttogether.model.Block;

public class BlockService implements GenericService<Block, Integer> {

	BlockRepository blockRepository;
	RecipeRepository recipeRepository;
	
	@Override
	public List<Block> findAll() {
		return blockRepository.findAll();
	}

	@Override
	public Block findById(Integer id) {
		return blockRepository.findById(id).orElse(null);
	}

	@Override
	public Block save(Block elt) {
		recipeRepository.saveAll(elt.getRecipesImAComponent());
		recipeRepository.saveAll(elt.getRecipesToMakeMe());
		return blockRepository.save(elt);
	}

	@Override
	public void deleteById(Integer id) {
		blockRepository.deleteById(id);
	}

	@Override
	public Block update(Block elt) {
		return blockRepository.save(elt);
	}


}
