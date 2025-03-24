package com.example.libraryapi.services;

import com.example.libraryapi.exceptions.OperacaoNaoPermitidaExeption;
import com.example.libraryapi.model.Autor;
import com.example.libraryapi.repositories.AutorRepository;
import com.example.libraryapi.repositories.LivroRepository;
import com.example.libraryapi.validator.AutorValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AutorSevice {

    private final AutorRepository repository;
    private final AutorValidator validator;
    private final LivroRepository livroRepository;


    public Autor salvar(Autor autor){
        validator.validar(autor);
        return this.repository.save(autor);
    }

    public Autor atualizar(Autor autor){
        if(autor.getId() == null){
            throw new IllegalArgumentException("Para atualizar já deve existir um cadastro");
        }
        validator.validar(autor);
        return this.repository.save(autor);
    }

    public Optional<Autor> obterPorId(UUID id){
        return this.repository.findById(id);
    }

    public void deletaPorId(UUID id){
        Optional<Autor> autorEncontrado = this.repository.findById(id);
        if(autorEncontrado.isPresent() && possuiLivro(autorEncontrado)){
            throw new OperacaoNaoPermitidaExeption("Não é permitido excluir autor que possui livros cadastrados!");
        }
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

    public List<Autor> pesquisar(String nome, String nacionalidade){
        Autor autor = new Autor();
        autor.setNome(nome);
        autor.setNacionalidade(nacionalidade);
        // Faz pesquisa personalizada, funciona atráves de um exemplo
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreNullValues()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Autor> autorExample = Example.of(autor,matcher);

        return repository.findAll(autorExample);
    }

    public boolean possuiLivro(Optional<Autor> autor){
        return this.livroRepository.existsByAutor(autor);
    }

}
