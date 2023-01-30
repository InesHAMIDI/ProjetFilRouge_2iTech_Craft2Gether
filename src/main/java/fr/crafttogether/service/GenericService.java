package fr.crafttogether.service;

import java.util.List;

import org.springframework.data.repository.NoRepositoryBean;

import fr.crafttogether.model.GenericEntity;

@NoRepositoryBean
public interface GenericService<T extends GenericEntity<T>> {
	
	public List<T> findAll();
    public T findById(int id);
    public T save(T elt);
    public void deleteById(int id) ;
    public T update(T elt);
}
