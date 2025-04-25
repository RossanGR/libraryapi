package com.example.libraryapi.controller;

import com.example.libraryapi.controller.dto.LivroRequestDTO;
import com.example.libraryapi.controller.dto.LivroResponseDTO;
import com.example.libraryapi.mappers.LivroMapper;
import com.example.libraryapi.model.GeneroLivro;
import com.example.libraryapi.model.Livro;
import com.example.libraryapi.services.LivroService;
import com.example.libraryapi.validator.LivroValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

import java.util.UUID;

@RestController
@RequestMapping("/livros")
@RequiredArgsConstructor
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO) // para resolver um erro de serialização que o java reclama
public class LivroController implements GenericController {

    private final LivroService service;
    private final LivroMapper mapper;
    private final LivroValidator validator;

    @PostMapping
    @PreAuthorize("hasAnyRole('OPERADOR','GERENTE')") // @PreAuthorize só é habilitado se o método estiver na classe de segurança e tiver o  @EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
    public ResponseEntity<Object> salvar(@RequestBody @Valid LivroRequestDTO dto) {
        Livro livro = mapper.toEntity(dto);
        service.salvar(livro);
        var url = gerarHeaderLocation(livro.getId());
        return ResponseEntity.created(url).build();
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('OPERADOR','GERENTE')")
    public ResponseEntity<LivroResponseDTO> obterDetalhes(@PathVariable("id") String id){
        return service.obterPorId(UUID.fromString(id)).map(livro -> {
                var dto = mapper.toDTO(livro);
                return ResponseEntity.ok(dto);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyRole('OPERADOR','GERENTE')")
    public ResponseEntity<Object> deletar(@PathVariable("id") String id) {
        var livro = service.obterPorId(UUID.fromString(id));
        if (livro.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        service.deletar(livro.get());
        return ResponseEntity.noContent().build();

    }

    @GetMapping
    @PreAuthorize("hasAnyRole('OPERADOR','GERENTE')")
    public ResponseEntity<Page<LivroResponseDTO>> pesquisa(
            @RequestParam(value = "isbn", required = false) String isbn,
            @RequestParam(value = "titulo", required = false) String titulo,
            @RequestParam(value = "nome-autor", required = false) String nomeAutor,
            @RequestParam(value = "genero", required = false) GeneroLivro genero,
            @RequestParam(value = "ano-publicacao", required = false) Integer dataPublicacao,
            @RequestParam(value= "pagina",defaultValue = "0") Integer pagina,
            @RequestParam(value= "tamanho-pagina",defaultValue = "10") Integer tamanhoPagina
    ) {
        Page<Livro>  paginaResultado = service.pesquisa(isbn, titulo, nomeAutor, genero, dataPublicacao, pagina, tamanhoPagina);
        Page<LivroResponseDTO> resultado = paginaResultado.map(mapper::toDTO);


        return ResponseEntity.ok(resultado);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAnyRole('OPERADOR','GERENTE')")
    public ResponseEntity<Object> atualizar(@PathVariable("id") String id, @RequestBody @Valid LivroRequestDTO dto){
        return service.obterPorId(UUID.fromString(id))
                .map(livro -> {
//                    Livro entidadeAux = mapper.toEntity(dto);
//                    livro.setDataPublicacao(entidadeAux.getDataPublicacao());
//                    livro.setIsbn(entidadeAux.getIsbn());
//                    livro.setPreco(entidadeAux.getPreco());
//                    livro.setGenero(entidadeAux.getGenero());
//                    livro.setTitulo(entidadeAux.getTitulo());
//                    livro.setAutor(entidadeAux.getAutor());
                    mapper.atualizarLivroFromDTO(dto, livro);
                    service.atualizar(livro);
                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
