package com.example.api.controller;

import com.example.api.agregates.requests.ProductoRequest;
import com.example.api.agregates.requests.VarianteRequest;
import com.example.api.entity.Categorias;
import com.example.api.entity.Generos;
import com.example.api.entity.TipoProducto;
import com.example.api.entity.VarianteProducto;
import com.example.api.service.CategoriaService;
import com.example.api.service.GeneroService;
import com.example.api.service.ProductoService;
import com.example.api.service.VarianteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductoService productoService;
    private final GeneroService generoService;
    private final VarianteService varianteService;
    private final CategoriaService categoriaService;

    @PostMapping("/create")
    private String createProducto(@RequestBody ProductoRequest productoRequest){
        return productoService.crearProducto(productoRequest);
    }

    @GetMapping("/lista")
    private List<TipoProducto> listaProductos(){
        return productoService.listProductos();
    }

    @PostMapping("/{id}/variante")
    private VarianteProducto añadirVariante(@PathVariable String id, @RequestBody VarianteRequest varianteRequest) throws IOException {
        return varianteService.añadirVariante(id, varianteRequest);
    }

    @GetMapping("/{idProductoBase}/variantes")
    private List<VarianteProducto> variantesByProducto(@PathVariable Long idProductoBase){
        return varianteService.listVariantes(idProductoBase);
    }

    @GetMapping("/categorias")
    private List<Categorias> getCategorias(){
        return categoriaService.listCategorias();
    }

    @GetMapping("/generos")
    private List<Generos> getGeneros(){
        return generoService.listGeneros();
    }

}
