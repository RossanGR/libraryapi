package com.example.libraryapi.controller.common;

import com.example.libraryapi.model.GeneroLivro;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class GeneroLivroDeserializer extends JsonDeserializer<GeneroLivro> {
    @Override
    public GeneroLivro deserialize(com.fasterxml.jackson.core.JsonParser p, com.fasterxml.jackson.databind.DeserializationContext ctxt) throws java.io.IOException {
        String genero = p.getText();
        if (genero == null || genero.trim().isEmpty()) {
            return null;
        }
        try{
            return GeneroLivro.valueOf(genero.toUpperCase());
        }catch (IllegalArgumentException e){
            throw new com.fasterxml.jackson.databind.exc.InvalidFormatException(p, "Gênero inválido", genero, GeneroLivro.class);
        }
    }
}
