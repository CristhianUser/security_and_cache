package com.example.api.repository;

import com.example.api.entity.Generos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeneroRepository extends JpaRepository<Generos, Long> {
    Generos findByNombreGenero(String genero);
}
