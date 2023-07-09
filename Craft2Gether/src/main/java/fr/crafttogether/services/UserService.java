package fr.crafttogether.services;

import fr.crafttogether.exceptions.NotFoundException;
import fr.crafttogether.models.User;
import fr.crafttogether.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.Collection;
@Service
@Secured("ROLE_ADMIN")
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Collection<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(int id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("no user with id " + id + " exists"));
    }

    public User findByUsername(String nom){ return userRepository.findByUsername(nom).orElseThrow(() -> new NotFoundException("no user with nom " + nom + " exists")); }

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
