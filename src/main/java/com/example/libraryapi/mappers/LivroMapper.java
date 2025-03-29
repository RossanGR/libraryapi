package com.example.libraryapi.mappers;

import com.example.libraryapi.controller.dto.LivroRequestDTO;
import com.example.libraryapi.controller.dto.LivroResponseDTO;
import com.example.libraryapi.model.Livro;
import com.example.libraryapi.repositories.AutorRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = AutorMapper.class) // o parametro uses serve para dizer que vai usar outro mapper
public abstract class LivroMapper {
    @Autowired
    AutorRepository autorRepository;

    @Mapping(target = "autor", expression = "java(autorRepository.findById(dto.idAutor()).orElse(null))")
    public abstract Livro toEntity(LivroRequestDTO dto);

    @Mapping(target = "autorDTO", source = "autor")
    public abstract LivroResponseDTO toDTO(Livro livro);
}

