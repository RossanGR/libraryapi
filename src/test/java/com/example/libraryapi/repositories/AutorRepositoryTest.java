package com.example.libraryapi.repositories;

import com.example.libraryapi.model.Autor;
import com.example.libraryapi.model.GeneroLivro;
import com.example.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository repository;

    @Autowired
    LivroRepository livroRepository;

    @Test
    public void salvarTest(){
        Autor autor = new Autor();
        autor.setNome("UmBerto");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(2024,2,22));

        var salvo = repository.save(autor);
        System.out.println("AUTOR: "+ salvo.toString());
    }

    @Test
    public void listarAutor(){
        List<Autor> lista = repository.findAll();
        lista.forEach(System.out::println);

    }

    @Test
    public void countAutor(){
        System.out.println("TOTAL: " + repository.count());
    }

    @Test
    void salvarAutorComLivrosTest(){
        Autor autor = new Autor();
        autor.setNome("Marcelo");
        autor.setNacionalidade("Canadense");
        autor.setDataNascimento(LocalDate.of(2024,2,22));

        Livro livro = new Livro();
        livro.setIsbn("65656565");
        livro.setPreco(BigDecimal.valueOf(200));
        livro.setGenero(GeneroLivro.ACAO);
        livro.setTitulo("Luppan");
        livro.setDataPublicacao(LocalDate.of(2020,2,22));
        livro.setAutor(autor);

        autor.setLivros(new ArrayList<>());
        autor.getLivros().add(livro);

        repository.save(autor);
//        livroRepository.saveAll(autor.getLivros());

    }
}
