package fr.crafttogether.repositories;

import fr.crafttogether.exceptions.NotFoundException;
import fr.crafttogether.models.Liste;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListeRepository extends JpaRepository<Liste, Integer> {
    Liste findByNom(String nom) throws NotFoundException;
}
