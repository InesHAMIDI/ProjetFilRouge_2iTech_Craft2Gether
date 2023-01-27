package fr.crafttogether.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.crafttogether.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
