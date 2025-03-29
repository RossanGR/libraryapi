package com.example.libraryapi.controller;

import com.example.libraryapi.controller.dto.ErroResposta;
import com.example.libraryapi.controller.dto.LivroRequestDTO;
import com.example.libraryapi.controller.dto.LivroResponseDTO;
import com.example.libraryapi.exceptions.OperacaoNaoPermitidaExeption;
import com.example.libraryapi.exceptions.RegistroDuplicadoException;
import com.example.libraryapi.mappers.LivroMapper;
import com.example.libraryapi.model.Livro;
import com.example.libraryapi.services.LivroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/livros")
@RequiredArgsConstructor
public class LivroController implements GenericController {

    private final LivroService service;
    private final LivroMapper mapper;

    @PostMapping
    public ResponseEntity<Object> salvar(@RequestBody @Valid LivroRequestDTO dto) {
        Livro livro = mapper.toEntity(dto);
        service.salvar(livro);
        var url = gerarHeaderLocation(livro.getId());
        return ResponseEntity.created(url).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<LivroResponseDTO> obterDetalhes(@PathVariable("id") String id){
        return service.obterPorId(UUID.fromString(id)).map(livro -> {
                var dto = mapper.toDTO(livro);
                return ResponseEntity.ok(dto);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
