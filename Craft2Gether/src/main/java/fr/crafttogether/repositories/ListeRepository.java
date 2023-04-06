package fr.crafttogether.repositories;

import fr.crafttogether.exceptions.NotFoundException;
import fr.crafttogether.models.Liste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ListeRepository extends JpaRepository<Liste, Integer> {
    Optional<Liste> findByTitre(String titre);
}
