package fr.crafttogether.services;

import fr.crafttogether.exceptions.NotFoundException;
import fr.crafttogether.models.Recette;
import fr.crafttogether.repositories.RecetteRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
@Service
public class RecetteService {
    @Autowired
    private RecetteRepository recetteRepository;

    public Collection<Recette> findAll() {
        return recetteRepository.findAll();
    }

    public Recette findById(int id) {
        return recetteRepository.findById(id).orElseThrow(() -> new NotFoundException("no recette with id " + id + " exists"));
    }

    public Recette findByNom(String nom){ return recetteRepository.findByNom(nom).orElseThrow(() -> new NotFoundException("no recette with nom " + nom + " exists")); }
}
