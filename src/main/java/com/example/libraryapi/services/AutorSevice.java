package com.example.libraryapi.services;

import com.example.libraryapi.model.Autor;
import com.example.libraryapi.repositories.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    public Autor atualizar(Autor autor){
        if(autor.getId() == null){
            throw new IllegalArgumentException("Para atualizar já deve existir um cadastro");
        }
        return this.repository.save(autor);
    }

    public Optional<Autor> obterPorId(UUID id){
        return this.repository.findById(id);
    }

    public void deletaPorId(UUID id){
        this.repository.deleteById(id);
    }

    public List<Autor> filtro(String nome, String nacionalidade){

        if(nome != null  && nacionalidade != null){
            return this.repository.findByNomeAndNacionalidade(nome,nacionalidade);
        }

        if(nome != null){
            return this.repository.findByNome(nome);
        }

        if(nacionalidade != null){
            return this.repository.findByNacionalidade(nacionalidade);
        }

        return this.repository.findAll();
    }
}
