package com.example.api.agregates.requests;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class ProductoRequest {
    private Long categoriaId;
    private Long generoId;
    private String nombreProducto;
    private String descripcionProducto;
    private Set<VarianteRequest> varianteRequestSet = new HashSet<>();
}
