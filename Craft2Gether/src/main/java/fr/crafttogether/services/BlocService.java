package fr.crafttogether.services;

import fr.crafttogether.exceptions.NotFoundException;
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
        return blocRepository.findById(id).orElseThrow(() -> new NotFoundException("no bloc with id " + id + " exists"));
    }

    public Bloc findByName(String name){ return blocRepository.findByName(name); }

    public Bloc save(Bloc bloc) {
        return blocRepository.save(bloc);
    }

    public void deleteById(int id) {
        if(blocRepository.findById(id).isEmpty())
            throw new NotFoundException("no bloc with id " + id + " exists");
        blocRepository.deleteById(id);
    }

    public Bloc update(Bloc bloc) {
        if(blocRepository.findById(bloc.getId()).isEmpty())
            throw new NotFoundException("no bloc with id " + bloc.getId() + " exists");
        return blocRepository.save(bloc);
    }

}
