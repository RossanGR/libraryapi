package com.example.libraryapi.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "autor")
@EntityListeners(AuditingEntityListener.class)
@Data // Lombock usa para criar getters e setter
//@Getter
//@Setter
public class Autor {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

//    public UUID getId() {
//        return id;
//    }

    @Column(length = 100, nullable = false)
    private String nome;

//    public String getNome(){
//        return this.nome;
//    }
//
//    public void setNome(String nome){
//        this.nome = nome;
//    }

    @Column(nullable = false)
    private LocalDate dataNascimento;

//    public LocalDate getDataNascimento(){
//        return this.dataNascimento;
//    }
//
//    public void setDataNascimento(LocalDate dataNascimento){
//        this.dataNascimento = dataNascimento;
//    }

    @Column(length = 50, nullable = false)
    private String nacionalidade;

//    public String getNacionalidade(){
//        return this.nacionalidade;
//    }
//
//    public void setNacionalidade(String nacionalidade){
//        this.nacionalidade = nacionalidade;
//    }

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL)
    private List<Livro> livros;

    @CreatedDate // Para toda vez que fizer uma criação, o JPA se responsabiliza por preencher com a data
    @Column(name = "data_cadastro")
    private LocalDateTime dataCadastro;

    @LastModifiedDate // Para toda vez que fizer uma modificação, o JPA se responsabiliza por preencher com a data
    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    @Column(name = "id_usuario")
    private UUID idUsuario;

}
