package fr.crafttogether.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import fr.crafttogether.model.GenericEntity;
import fr.crafttogether.service.GenericService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class GenericController<T extends GenericEntity<T>> {

    private final GenericService<T> service;
    

    @GetMapping("/{id}")
    public ResponseEntity<T> getOne(@PathVariable int id){
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("")
    public ResponseEntity<T> update(@RequestBody T updated){
        return ResponseEntity.ok(service.update(updated));
    }

    @PostMapping("")
    public ResponseEntity<T> create(@RequestBody T created){
        return ResponseEntity.ok(service.save(created));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id){
        service.deleteById(id);
        return ResponseEntity.ok("Ok");
    }
}
