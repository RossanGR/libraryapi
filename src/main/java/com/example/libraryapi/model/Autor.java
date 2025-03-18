package com.example.libraryapi.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "autor")
@Data // Lombock usa para criar getters e setter
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 100, nullable = false)
    private String nome;

    @Column(nullable = false)
    private LocalDate dataNascimento;

    @Column(length = 50, nullable = false)
    private String nacionalidade;

    @OneToMany(mappedBy = "autorId", cascade = CascadeType.ALL)
    private List<Livro> livros;

}
