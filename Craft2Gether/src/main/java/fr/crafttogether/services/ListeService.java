package fr.crafttogether.services;

import fr.crafttogether.exceptions.NotFoundException;
import fr.crafttogether.models.Liste;
import fr.crafttogether.repositories.ListeRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ListeService {
    private ListeRepository listeRepository;

    public List<Liste> findAll() {
        return listeRepository.findAll();
    }
    public Liste findById(int id) {
        return listeRepository.findById(id).orElseThrow(() -> new NotFoundException("no list with id " + id + " exists"));
    }
    public Liste findByNom(String nom){ return listeRepository.findByNom(nom); }


    public Liste save(Liste liste) {
        return listeRepository.save(liste);
    }

    public void deleteById(int id) {
        if(listeRepository.findById(id).isEmpty())
            throw new NotFoundException("no list with id " + id + " exists");
        listeRepository.deleteById(id);
    }

    public Liste update(Liste liste) {
        if(listeRepository.findById(liste.getId()).isEmpty())
            throw new NotFoundException("no list with id " + liste.getId() + " exists");
        return listeRepository.save(liste);
    }


}
