package com.example.api.service;

import com.example.api.agregates.requests.VentaRequest;
import com.example.api.entity.VentaProducto;

public interface RegistroVentaService {
    VentaProducto generarVenta(VentaRequest venta);
}
