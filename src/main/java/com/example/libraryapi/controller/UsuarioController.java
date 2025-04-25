package com.example.libraryapi.controller;

import com.example.libraryapi.controller.dto.UsuarioDTO;
import com.example.libraryapi.mappers.UsuarioMapper;
import com.example.libraryapi.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;
    private final UsuarioMapper usuarioMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void salvar(@RequestBody UsuarioDTO dto){
        System.out.println("Salvando usuario: " + dto);
        var usuario = usuarioMapper.toEntity(dto);
        usuarioService.salvar(usuario);
    }
}
