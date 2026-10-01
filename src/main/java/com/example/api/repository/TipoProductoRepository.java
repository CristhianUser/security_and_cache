package com.example.api.repository;

import com.example.api.entity.TipoProducto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoProductoRepository extends JpaRepository<TipoProducto, Long>{
    TipoProducto findByCodigoProducto(String codigoProducto);
}
