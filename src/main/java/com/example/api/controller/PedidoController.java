package com.example.api.controller;

import com.example.api.agregates.requests.VentaRequest;
import com.example.api.entity.VentaProducto;
import com.example.api.service.RegistroVentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/pedido")
@RequiredArgsConstructor
public class PedidoController {

    private final RegistroVentaService registroVentaService;

    @PostMapping("/crear-pedido")
    private VentaProducto crearPedido(@RequestBody Set<VentaRequest> pedidos){
        return registroVentaService.generarVenta(pedidos);
    }

}
