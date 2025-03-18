package com.example.libraryapi.services;

import com.example.libraryapi.model.Autor;
import com.example.libraryapi.repositories.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutorSevice {
    @Autowired
    private AutorRepository repository;

    public void AutorService(AutorRepository autorRepository){
        this.repository = autorRepository;
    }

    public Autor salvar(Autor autor){
        return this.repository.save(autor);
    }
}
