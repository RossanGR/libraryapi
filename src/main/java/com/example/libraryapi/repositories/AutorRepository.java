package com.example.libraryapi.repositories;

import com.example.libraryapi.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface AutorRepository extends JpaRepository<Autor, UUID> {

}
