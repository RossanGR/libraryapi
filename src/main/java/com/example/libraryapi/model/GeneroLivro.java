package com.example.libraryapi.model;
import com.example.libraryapi.controller.common.GeneroLivroDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = GeneroLivroDeserializer.class)
public enum GeneroLivro {
    FICCAO,
    FANTASIA,
    MISTERIAO,
    ACAO,
    CIENCIA
}
