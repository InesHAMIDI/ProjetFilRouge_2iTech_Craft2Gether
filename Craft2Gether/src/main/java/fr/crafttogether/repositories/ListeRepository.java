package fr.crafttogether.repositories;

import fr.crafttogether.models.Liste;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListeRepository extends JpaRepository<Liste, Long> {
}
