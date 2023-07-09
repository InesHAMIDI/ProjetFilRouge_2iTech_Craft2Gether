package fr.crafttogether.services;

import fr.crafttogether.exceptions.NotFoundException;
import fr.crafttogether.models.Bloc;
import fr.crafttogether.repositories.BlocRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
@Service
public class BlocService {
    @Autowired
    private BlocRepository blocRepository;

    public Collection<Bloc> findAll() {
        return blocRepository.findAll();
    }

    public Bloc findById(int id) {
        return blocRepository.findById(id).orElseThrow(() -> new NotFoundException("no bloc with id " + id + " exists"));
    }

    public Bloc findByNom(String nom)
    {
        return blocRepository.findByNom(nom).orElseThrow(() -> new NotFoundException("no bloc with nom " + nom + " exists"));
    }
}
