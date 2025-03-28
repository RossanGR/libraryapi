package com.example.libraryapi.controller.dto;

import com.example.libraryapi.model.GeneroLivro;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.ISBN;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record LivroRequestDTO(
        @ISBN
        @NotBlank(message = "ISBN deve ser informado")
        String isbn,

        @NotBlank(message = "Título deve ser informado")
        String titulo,

        @NotNull(message = "Data de publicação deve ser informada")
        @Past(message = "Data de publicação deve ser no passado")
        LocalDate dataPublicacao,

        @NotNull(message = "Genero deve ser informado")
        GeneroLivro genero,

        BigDecimal preco,

        @NotNull(message = "ID do autor deve ser informado")
        UUID idAutor
) {
}
