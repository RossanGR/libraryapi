package com.example.libraryapi.services;

import com.example.libraryapi.model.GeneroLivro;
import com.example.libraryapi.model.Livro;
import com.example.libraryapi.repositories.LivroRepository;
import com.example.libraryapi.repositories.specs.LivroSpecs;
import com.example.libraryapi.validator.LivroValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service

public class LivroService {
    @Autowired
    private LivroRepository repository;
    @Autowired
    private LivroValidator validator;

    public void salvar(Livro livro) {
        validator.validar(livro);
        repository.save(livro);
    }

    public Optional<Livro> obterPorId(UUID id){
        return repository.findById(id);
    }

    public void deletar(Livro livro) {
        repository.delete(livro);
    }

    public Page<Livro> pesquisa(
            String isbn,
            String titulo,
            String nomeAutor,
            GeneroLivro genero,
            Integer anoPublicacao,
            Integer pagina,
            Integer tamanhoPagina){
        Specification<Livro> specs = Specification.where((root, query, criteriaBuilder) -> criteriaBuilder.conjunction());

        if(isbn != null){
            specs = specs.and(LivroSpecs.isbnEqual(isbn));
        }

        if (titulo != null){
            specs = specs.and(LivroSpecs.tituloLike(titulo));
        }

        if(genero != null){
            specs = specs.and(LivroSpecs.generoEqual(genero.toString()));
        }

        if(anoPublicacao != null){
            specs = specs.and(LivroSpecs.anoPublicacaoEqual(anoPublicacao));
        }

        if(nomeAutor != null){
            specs = specs.and(LivroSpecs.nomeAutorLike(nomeAutor));
        }
        Pageable pageRequest = PageRequest.of(pagina, tamanhoPagina);

        return repository.findAll(specs,pageRequest);
    }

    public void atualizar(Livro livro) {
        if (livro.getId() == null){
            throw new IllegalArgumentException("Para atualizar, é necessário que o autor já esteja salvo na base");
        }
        validator.validar(livro);
        repository.save(livro);
    }
}
