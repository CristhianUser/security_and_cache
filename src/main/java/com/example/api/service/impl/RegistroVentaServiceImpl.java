package com.example.api.service.impl;
import com.example.api.agregates.requests.ItemsRequest;
import com.example.api.agregates.requests.VentaRequest;
import com.example.api.entity.*;
import com.example.api.repository.*;
import com.example.api.service.RegistroVentaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RegistroVentaServiceImpl implements RegistroVentaService {

    private final VentaProductoRepository ventaProductoRepository;
    private final DetalleVentaRepository detalleVentaRepository;
    private final VarianteProductoRepository varianteProductoRepository;
    private final UserRepository userRepository;
    private final MetodoPagoRepository metodoPagoRepository;
    private final PagoRepository pagoRepository;

    @Transactional
    @Override
    public VentaProducto generarVenta(VentaRequest venta) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario usuarioLog = userRepository.findByEmail(email);
        List<MetodoPago> metodosUsuario = metodoPagoRepository.findByPagoUsuario(usuarioLog);

        if(metodosUsuario.size() == 0){
            throw new RuntimeException("No cuentas con metodos de pago registados");
        }

        MetodoPago metodoPagoRelacionado = metodoPagoRepository.findByIdAndByPagoUsuarioIdUser(venta.getMetodoSeleccionado(), usuarioLog.getIdUser());

        if (metodoPagoRelacionado == null){
            throw new RuntimeException("No te pertenece el metodo seleccionado, realizaste cambios");
        }else {
            VentaProducto ventaProducto = new VentaProducto();
            ventaProducto.setUsuario(usuarioLog);
            Double totalGeneral = 0.00;
            for (ItemsRequest item: venta.getItems()){
                VarianteProducto producto = varianteProductoRepository.findByCodigoVariante(item.getCodigoVariante());
                DetalleVenta detalleVenta = new DetalleVenta();
                detalleVenta.setVarianteProducto(producto);
                detalleVenta.setPrecioUnitario(producto.getPrecio());
                detalleVenta.setProductoCantidad(item.getCantidadVariante());
                Double subTotal = producto.getPrecio() * item.getCantidadVariante();
                detalleVenta.setPrecioTotal(subTotal);
                ventaProducto.vincularDetalle(detalleVenta);
                totalGeneral+=subTotal;
            }
            ventaProducto.setVentaTotal(totalGeneral);
            Pago nuevoPago = new Pago();
            nuevoPago.setMetodo(metodoPagoRelacionado);
            nuevoPago.setMonto(totalGeneral);
            pagoRepository.save(nuevoPago);
            ventaProducto.setPago(nuevoPago);
            log.info("Venta generada por : {}, detalles :{}", email, venta.getItems().size());
            return ventaProductoRepository.save(ventaProducto);
        }
    }
}
