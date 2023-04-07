package fr.crafttogether.services;

import fr.crafttogether.exceptions.NotFoundException;
import fr.crafttogether.models.Liste;
import fr.crafttogether.repositories.ListeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
@Service
public class ListeService {
    @Autowired
    private ListeRepository listeRepository;

    public Collection<Liste> findAll() {
        return listeRepository.findAll();
    }
    public Liste findById(int id) {
        return listeRepository.findById(id).orElseThrow(() -> new NotFoundException("no list with id " + id + " exists"));
    }
    public Liste findByTitre(String titre){ return listeRepository.findByTitre(titre).orElseThrow(() -> new NotFoundException("no list with nom " + titre + " exists")); }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public Liste save(Liste liste) {
        return listeRepository.save(liste);
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteById(int id) {
        if(listeRepository.findById(id).isEmpty())
            throw new NotFoundException("no list with id " + id + " exists");
        listeRepository.deleteById(id);
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public Liste update(Liste liste) {
        if(listeRepository.findById(liste.getId()).isEmpty())
            throw new NotFoundException("no list with id " + liste.getId() + " exists");
        return listeRepository.save(liste);
    }
}
