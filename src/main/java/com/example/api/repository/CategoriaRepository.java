package com.example.api.repository;

import com.example.api.entity.Categorias;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categorias, Long>{
    Categorias findByNombreCategoria(String nombreCategoria);
}
