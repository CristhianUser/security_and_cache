package com.example.api.repository;

import com.example.api.entity.VarianteProducto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VarianteProductoRepository extends JpaRepository<VarianteProducto, Long> {
    List<VarianteProducto> findByCodigoVarianteContainingIgnoreCase(String idProducto);
    List<VarianteProducto> findByTipoProductoId(Long idProductoPadre);
    List<VarianteProducto> findByTipoProductoCodigoProducto(String codigoProductoBase);
    VarianteProducto findByCodigoVariante(String codigoVariante);
    void deleteByCodigoVariante(String codigoVariante);
}
