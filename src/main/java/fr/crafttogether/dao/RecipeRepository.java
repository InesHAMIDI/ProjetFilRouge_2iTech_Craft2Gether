package fr.crafttogether.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.crafttogether.model.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {

}
