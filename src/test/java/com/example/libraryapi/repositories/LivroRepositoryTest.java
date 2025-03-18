package com.example.libraryapi.repositories;

import com.example.libraryapi.model.Autor;
import com.example.libraryapi.model.GeneroLivro;
import com.example.libraryapi.model.Livro;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@SpringBootTest
class LivroRepositoryTest {

    @Autowired
    LivroRepository repository;

    @Autowired
    AutorRepository autorRepository;

    @Test
    void salvarCascadeLivro(){
        Livro livro = new Livro();
        livro.setIsbn("65656565");
        livro.setPreco(BigDecimal.valueOf(200));
        livro.setGenero(GeneroLivro.ACAO);
        livro.setTitulo("TESTE");
        livro.setDataPublicacao(LocalDate.of(2020,2,22));

        Autor autor = new Autor();
        autor.setNome("Dois Berto");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(2024,2,22));

        livro.setAutorId(autor);

        repository.save(livro);
    }


}
