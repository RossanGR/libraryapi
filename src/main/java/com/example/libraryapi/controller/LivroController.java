package com.example.libraryapi.controller;

import com.example.libraryapi.controller.dto.ErroResposta;
import com.example.libraryapi.controller.dto.LivroRequestDTO;
import com.example.libraryapi.exceptions.OperacaoNaoPermitidaExeption;
import com.example.libraryapi.exceptions.RegistroDuplicadoException;
import com.example.libraryapi.services.LivroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/livros")
public class LivroController {
    @Autowired
    private LivroService service;

    @PostMapping
    public ResponseEntity<Object> salvar(@RequestBody @Valid LivroRequestDTO livroRequestDTO) {
        try {
//            return service.salvar(livroRequestDTO);
            return ResponseEntity.ok("Livro salvo com sucesso");
        } catch (OperacaoNaoPermitidaExeption e) {
            ErroResposta erroDTO = ErroResposta.conflito(e.getMessage());
            return ResponseEntity.status(erroDTO.status()).body(erroDTO);
        }
    }
}
