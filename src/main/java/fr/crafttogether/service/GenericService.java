package fr.crafttogether.service;

import java.util.List;

public interface GenericService<T,I> {
	
	public List<T> findAll();
    public T findById(I id);
    public T save(T elt);
    public void deleteById(I id) ;
    public T update(T elt);
}
