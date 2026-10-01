package com.example.api.service.impl;

import com.example.api.agregates.requests.VarianteRequest;
import com.example.api.entity.TipoProducto;
import com.example.api.entity.VarianteProducto;
import com.example.api.repository.TipoProductoRepository;
import com.example.api.repository.VarianteProductoRepository;
import com.example.api.service.UploadFile;
import com.example.api.service.VarianteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VarianteServiceImpl implements VarianteService {

    private final TipoProductoRepository tipoProductoRepository;
    private final VarianteProductoRepository varianteProductoRepository;
    private final UploadFile uploadFile;

    @Override
    public List<VarianteProducto> listVariantes(Long idProductoBase) {
        return varianteProductoRepository.findByTipoProductoId(idProductoBase);
    }

    @Override
    @Transactional
    public VarianteProducto añadirVariante(String idTipoProducto, VarianteRequest varianteRequest) throws IOException {
        TipoProducto producto = tipoProductoRepository.findByCodigoProducto(idTipoProducto);
        VarianteProducto varianteProducto = getEntity(producto, varianteRequest);
        producto.agregarVariante(varianteProducto);
        tipoProductoRepository.save(producto);
        return varianteProducto;
    }

    @Override
    public VarianteProducto actualizarStock(String codigoVariante, Integer nuevoStock) {
        VarianteProducto varianteActualizarStock = varianteProductoRepository.findByCodigoVariante(codigoVariante);
        varianteActualizarStock.setStock(nuevoStock);
        return varianteProductoRepository.save(varianteActualizarStock);
    }

    @Transactional
    @Override
    public void eliminarVariante(String idVarianteProducto) {
        VarianteProducto variante = varianteProductoRepository.findByCodigoVariante(idVarianteProducto);
        TipoProducto productoVinculado = variante.getTipoProducto();
        productoVinculado.removerVariante(variante);
        varianteProductoRepository.deleteByCodigoVariante(idVarianteProducto);
    }

    public VarianteProducto getEntity(TipoProducto producto,VarianteRequest varianteRequest) throws IOException {
        VarianteProducto variante = new VarianteProducto();
        String color = varianteRequest.getColor().trim().length() >= 3 ? varianteRequest.getColor().substring(0,3).toUpperCase() : varianteRequest.getColor().toUpperCase();
        String talla = varianteRequest.getTalla().trim().length() > 0 ? varianteRequest.getTalla().toUpperCase() : null;
        variante.setCodigoVariante(producto.getCodigoProducto()+"-" +color+ "-"+talla);
        variante.setBarras(producto.getCodigoProducto()+"-"+color+"-"+talla);
        variante.setTalla(varianteRequest.getTalla());
        variante.setColor(varianteRequest.getColor());
        variante.setPrecio(varianteRequest.getPrecio());
        variante.setStock(varianteRequest.getStock());
        boolean estado = varianteRequest.getStock() > 0 ? true : false;
        variante.setEstado(estado);
        variante.setFoto1(uploadFile.mapUrlBySaveFile(varianteRequest.getFoto1()));
        variante.setFoto2(uploadFile.mapUrlBySaveFile(varianteRequest.getFoto2()));
        variante.setFoto3(uploadFile.mapUrlBySaveFile(varianteRequest.getFoto3()));
        return variante;
    }

}
