package com.example.libraryapi.repositories.specs;

import com.example.libraryapi.model.Livro;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

public class LivroSpecs {

    public static Specification<Livro> isbnEqual(String isbn){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("isbn"), isbn);
    }

    public static Specification<Livro> tituloLike(String titulo){
        return ((root, query, criteriaBuilder) -> criteriaBuilder
                .like(criteriaBuilder.upper(root.get("titulo")), "%" + titulo.toUpperCase() + "%"));
    }

    public static Specification<Livro> generoEqual(String genero){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("genero"), genero);
    }

    public static Specification<Livro> anoPublicacaoEqual(Integer anoPublicacao){
        return (root, query, criteriaBuilder) -> criteriaBuilder
                .equal(
                        criteriaBuilder.function("to_char", String.class, root.get("dataPublicacao"), criteriaBuilder.literal("YYYY")),
                        anoPublicacao.toString()
                );
    }

    public static Specification<Livro> nomeAutorLike(String nome){
//        Primeira Forma
//        return (root, query, criteriaBuilder) -> {
//            return criteriaBuilder.like(
//                    criteriaBuilder.upper(root.get("autor").get("nome")),
//                    "%" + nome.toUpperCase() + "%"
//            );
//        };
//        Segunda Forma
//        return (root, query, criteriaBuilder) -> criteriaBuilder
//                .like(criteriaBuilder.upper(root.join("autor").get("nome")), "%" + nome.toUpperCase() + "%");
//        Terceira Forma
        return (root, query,criteriaBuilder)->{
            Join<Object,Object> joinAutor = root.join("autor", JoinType.LEFT);
            return criteriaBuilder.like(criteriaBuilder.upper(joinAutor.get("nome")), "%" + nome.toUpperCase() + "%");
        };
    }
}
