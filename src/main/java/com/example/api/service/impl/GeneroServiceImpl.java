package com.example.api.service.impl;

import com.example.api.entity.Generos;
import com.example.api.repository.GeneroRepository;
import com.example.api.service.GeneroService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GeneroServiceImpl implements GeneroService {

    private final GeneroRepository generoRepository;

    @Override
    public List<Generos> listGeneros() {
        return generoRepository.findAll();
    }
}
