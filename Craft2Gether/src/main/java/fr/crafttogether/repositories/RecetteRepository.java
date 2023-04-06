package fr.crafttogether.repositories;

import fr.crafttogether.models.Recette;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecetteRepository extends JpaRepository<Recette, Integer>  {
    Recette findByName(String name);
}
