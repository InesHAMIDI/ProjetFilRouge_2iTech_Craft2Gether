package fr.crafttogether.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.crafttogether.model.Block;

public interface BlockRepository extends JpaRepository<Block, Integer> {

}
