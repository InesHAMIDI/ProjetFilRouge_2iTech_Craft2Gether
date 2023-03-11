package fr.crafttogether.repositories;

import fr.crafttogether.models.Bloc;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlocRepository extends JpaRepository<Bloc, Long> {
        }
