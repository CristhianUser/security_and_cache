package com.example.api.service.impl;

import com.example.api.agregates.requests.ProductoRequest;
import com.example.api.entity.*;
import com.example.api.repository.CategoriaRepository;
import com.example.api.repository.GeneroRepository;
import com.example.api.repository.TipoProductoRepository;
import com.example.api.service.ProductoService;
import com.example.api.service.UploadFile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final CategoriaRepository categoriaRepository;
    private final GeneroRepository generoRepository;
    private final TipoProductoRepository tipoProductoRepository;
    private final UploadFile uploadFile;

    @Transactional
    @Override
    public String crearProducto(ProductoRequest productoRequest) {
        TipoProducto producto = new TipoProducto();
        String codigo = UUID.randomUUID().toString().replace("-", "").substring(0, 5);
        Categorias categoria = getCategoria(productoRequest.getCategoriaId());
        Generos genero = getGenero(productoRequest.getGeneroId());
        String categoriaNombre = categoria.getNombreCategoria().substring(0,3);
        String idProductoBase = categoriaNombre + "-" + genero.getNombreGenero() + "-" + codigo;
        producto.setCodigoProducto(idProductoBase);
        producto.setNombreProducto(productoRequest.getNombreProducto());
        producto.setCategoria(categoria);
        producto.setGenero(genero);
        producto.setDescripcionProducto(productoRequest.getDescripcionProducto());

        if(productoRequest.getVarianteRequestSet() != null && !productoRequest.getVarianteRequestSet().isEmpty()){
            productoRequest.getVarianteRequestSet().forEach(variante -> {
                VarianteProducto nuevaVariante = new VarianteProducto();
                String prefijoColor = variante.getColor().trim().length() >= 3
                        ? variante.getColor().substring(0,3).toUpperCase() : variante.getColor().toUpperCase();
                String prefijoTalla = variante.getTalla().trim().length() > 0
                        ? variante.getTalla().toUpperCase() : "";
                String idVariante = idProductoBase + "-" + prefijoColor + "-" + prefijoTalla;
                boolean estado = variante.getStock() > 0 ? true : false;
                nuevaVariante.setCodigoVariante(idVariante);
                nuevaVariante.setBarras(idVariante);
                nuevaVariante.setTalla(variante.getTalla());
                nuevaVariante.setColor(variante.getColor());
                nuevaVariante.setStock(variante.getStock());
                nuevaVariante.setPrecio(variante.getPrecio());
                nuevaVariante.setEstado(estado);
                try {
                    nuevaVariante.setFoto1(uploadFile.mapUrlBySaveFile(variante.getFoto1()));
                    nuevaVariante.setFoto2(uploadFile.mapUrlBySaveFile(variante.getFoto2()));
                     nuevaVariante.setFoto3(uploadFile.mapUrlBySaveFile(variante.getFoto3()));
                } catch (Exception e) {
                    throw new RuntimeException("Error al procesar una de las imagenes: ", e);
                }
                producto.agregarVariante(nuevaVariante);
            });
        }
        tipoProductoRepository.save(producto);
        return "Se guardo de manera correcta el producto: "+idProductoBase;
    }

    @Override
    public List<TipoProducto> listProductos() {
        return tipoProductoRepository.findAll();
    }

    public Categorias getCategoria(Long categoriaId){
        return categoriaRepository.findById(categoriaId).orElseThrow(() -> new RuntimeException("Error al seleccionar la categoria"));
    }

    public Generos getGenero(Long generoId){
        return generoRepository.findById(generoId).orElseThrow(() -> new RuntimeException("Error al seleccionar el genero"));
    }

}
