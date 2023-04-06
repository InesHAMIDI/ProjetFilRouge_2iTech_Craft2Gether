package fr.crafttogether.services;

import fr.crafttogether.exceptions.NotFoundException;
import fr.crafttogether.models.Recette;
import fr.crafttogether.repositories.RecetteRepository;

import java.util.List;

public class RecetteService {
    RecetteRepository recetteRepository;

    public List<Recette> findAll() {
        return recetteRepository.findAll();
    }

    public Recette findById(int id) {
        return recetteRepository.findById(id).orElseThrow(() -> new NotFoundException("no recette with id " + id + " exists"));
    }

    public Recette findByNom(String name){ return recetteRepository.findByName(name); }

    public Recette save(Recette recette) {
        return recetteRepository.save(recette);
    }

    public void deleteById(int id) {
        if(recetteRepository.findById(id).isEmpty())
            throw new NotFoundException("no recette with id " + id + " exists");
        recetteRepository.deleteById(id);
    }

    public Recette update(Recette recette) {
        if(recetteRepository.findById(recette.getId()).isEmpty())
            throw new NotFoundException("no recette with id " + recette.getId() + " exists");
        return recetteRepository.save(recette);
    }
}
