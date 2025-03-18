package com.example.libraryapi.model;


import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "livro")
@Data // Lombock usa para criar getters e setter
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 20, nullable = false)
    private String isbn;

    @Column(length = 150, nullable = false)
    private String titulo;

    @Column(nullable = false)
    private LocalDate dataPublicacao;

    @Enumerated(EnumType.STRING) // Serve para dizer que é um enum e o parâmetro é o tipo do enum
    @Column(length = 30, nullable = false)
    private GeneroLivro genero;

    @Column(nullable = false, precision = 18, scale = 2)
    private BigDecimal preco;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "autor_id")
    private Autor autorId;
}
