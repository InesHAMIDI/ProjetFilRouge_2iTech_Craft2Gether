package fr.crafttogether.repositories;

import fr.crafttogether.models.Bloc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlocRepository extends JpaRepository<Bloc, Integer> {
    Optional<Bloc> findByNom(String nom);
}

