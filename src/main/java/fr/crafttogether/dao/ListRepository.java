package fr.crafttogether.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.crafttogether.model.List;

public interface ListRepository extends JpaRepository<List, Integer> {

}
