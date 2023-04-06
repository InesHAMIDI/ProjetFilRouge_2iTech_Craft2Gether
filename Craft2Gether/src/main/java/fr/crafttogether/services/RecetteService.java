package fr.crafttogether.services;

import fr.crafttogether.exceptions.NotFoundException;
import fr.crafttogether.models.Recette;
import fr.crafttogether.repositories.RecetteRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class RecetteService {
    RecetteRepository recetteRepository;

    public List<Recette> findAll() {
        return recetteRepository.findAll();
    }

    public Recette findById(int id) {
        return recetteRepository.findById(id).orElseThrow(() -> new NotFoundException("no recette with id " + id + " exists"));
    }

    public Recette findByNom(String nom){ return recetteRepository.findByNom(nom).orElseThrow(() -> new NotFoundException("no recette with nom " + nom + " exists")); }
}
