package fr.crafttogether.repositories;

import fr.crafttogether.models.Recette;
import fr.crafttogether.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
