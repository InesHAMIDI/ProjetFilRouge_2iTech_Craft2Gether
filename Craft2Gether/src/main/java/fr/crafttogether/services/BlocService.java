package fr.crafttogether.services;

import fr.crafttogether.models.Bloc;
import fr.crafttogether.repositories.BlocRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BlocService {

    BlocRepository blocRepository;

    public List<Bloc> findAll() {
        return blocRepository.findAll();
    }

    public Bloc findById(int id) {
        return blocRepository.findById(id).orElse(null);
    }

    public Bloc save(Bloc bloc) {
        return blocRepository.save(bloc);
    }

    public void deleteById(int id) {
        blocRepository.deleteById(id);
    }

    public Bloc update(Bloc bloc) {
        return blocRepository.save(bloc);
    }

}
