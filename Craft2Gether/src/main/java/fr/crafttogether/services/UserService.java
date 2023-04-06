package fr.crafttogether.services;

import fr.crafttogether.exceptions.NotFoundException;
import fr.crafttogether.models.User;
import fr.crafttogether.repositories.UserRepository;

import java.util.List;

public class UserService {

    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(int id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("no user with id " + id + " exists"));
    }

    public User findByName(String name){ return userRepository.findByName(name); }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void deleteById(int id) {
        if(userRepository.findById(id).isEmpty())
            throw new NotFoundException("no user with id " + id + " exists");
        userRepository.deleteById(id);
    }

    public User update(User user) {
        if(userRepository.findById(user.getId()).isEmpty())
            throw new NotFoundException("no user with id " + user.getId() + " exists");
        return userRepository.save(user);
    }
}
