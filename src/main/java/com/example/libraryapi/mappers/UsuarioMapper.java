package com.example.libraryapi.mappers;

import com.example.libraryapi.controller.dto.UsuarioDTO;
import com.example.libraryapi.model.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    UsuarioDTO toDto(Usuario usuario);
    Usuario toEntity(UsuarioDTO dto);
}
