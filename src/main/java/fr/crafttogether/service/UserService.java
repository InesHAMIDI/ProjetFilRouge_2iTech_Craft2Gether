package fr.crafttogether.service;

import java.util.List;

import fr.crafttogether.dao.ListRepository;
import fr.crafttogether.dao.UserRepository;
import fr.crafttogether.model.User;

public class UserService implements GenericService<User, Integer>{

	UserRepository userRepository;
	ListRepository listRepository;
	
	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User findById(Integer id) {
		return userRepository.findById(id).orElse(null);
	}

	@Override
	public User save(User elt) {
//		listRepository.saveAll(elt.getLists());
//		return userRepository.save(elt);
		return null;
	}

	@Override
	public void deleteById(Integer id) {
		userRepository.deleteById(id);
		
	}

	@Override
	public User update(User elt) {
		return userRepository.save(elt);
	}

}
