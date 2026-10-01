package com.example.api.service;

import com.example.api.agregates.requests.VarianteRequest;
import com.example.api.entity.VarianteProducto;

import java.io.IOException;
import java.util.List;

public interface VarianteService {
    List<VarianteProducto> listVariantes(Long idProductoPadre);
    VarianteProducto añadirVariante(String idTipoProducto, VarianteRequest varianteRequest) throws IOException;
    VarianteProducto actualizarStock(String codigoVariante, Integer nuevoStock);
    void eliminarVariante(String idVarianteProducto);
}
