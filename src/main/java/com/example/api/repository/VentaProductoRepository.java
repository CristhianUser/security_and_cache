package com.example.api.repository;

import com.example.api.entity.VentaProducto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaProductoRepository extends JpaRepository<VentaProducto, String> {
}
