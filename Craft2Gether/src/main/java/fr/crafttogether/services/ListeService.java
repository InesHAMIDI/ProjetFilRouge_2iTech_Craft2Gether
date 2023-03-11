package fr.crafttogether.services;

import fr.crafttogether.models.Liste;
import fr.crafttogether.repositories.ListeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ListeService {
    private ListeRepository listeRepository;
    public List<Liste> findAll() {
        return listeRepository.findAll();
    }
    public Liste findById(long id) {
        return listeRepository.findById(id).orElse(null);
    }
    public Liste save(Liste liste) {
        return listeRepository.save(liste);
    }
    public void deleteById(long id){
        listeRepository.deleteById(id);
    }
    public Liste update(Liste liste){
        return listeRepository.save(liste);
    }

}
