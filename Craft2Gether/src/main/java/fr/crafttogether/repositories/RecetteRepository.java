package fr.crafttogether.repositories;

import fr.crafttogether.models.Recette;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecetteRepository extends JpaRepository<Recette, Integer>  {
    Optional<Recette> findByNom(String nom);
}
