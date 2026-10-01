package com.example.api.service;

import com.example.api.agregates.requests.ProductoRequest;
import com.example.api.entity.TipoProducto;

import java.util.List;

public interface ProductoService {
    String crearProducto(ProductoRequest productoRequest);
    List<TipoProducto> listProductos();
}