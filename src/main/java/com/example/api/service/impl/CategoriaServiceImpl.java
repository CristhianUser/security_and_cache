package com.example.api.service.impl;

import com.example.api.entity.Categorias;
import com.example.api.repository.CategoriaRepository;
import com.example.api.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    @Override
    public List<Categorias> listCategorias() {
        return categoriaRepository.findAll();
    }
}
