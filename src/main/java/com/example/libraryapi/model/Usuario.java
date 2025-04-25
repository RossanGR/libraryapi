package com.example.libraryapi.model;

import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "usuario")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private String login;

    @Column
    private String senha;

    @Type(ListArrayType.class) // Serve para usar o tipo de dado array no banco de dados
    @Column(name = "role", columnDefinition = "varchar[]")
    private List<String> role;

    @CreatedDate // Para toda vez que fizer uma criação, o JPA se responsabiliza por preencher com a data
    @Column(name = "data_cadastro")
    private LocalDateTime dataCadastro;

    @LastModifiedDate // Para toda vez que fizer uma modificação, o JPA se responsabiliza por preencher com a data
    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

}
