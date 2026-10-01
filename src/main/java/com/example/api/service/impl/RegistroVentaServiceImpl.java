package com.example.api.service.impl;
import com.example.api.agregates.requests.ItemsRequest;
import com.example.api.agregates.requests.VentaRequest;
import com.example.api.entity.DetalleVenta;
import com.example.api.entity.Usuario;
import com.example.api.entity.VarianteProducto;
import com.example.api.entity.VentaProducto;
import com.example.api.repository.DetalleVentaRepository;
import com.example.api.repository.UserRepository;
import com.example.api.repository.VarianteProductoRepository;
import com.example.api.repository.VentaProductoRepository;
import com.example.api.service.RegistroVentaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class RegistroVentaServiceImpl implements RegistroVentaService {

    private final VentaProductoRepository ventaProductoRepository;
    private final DetalleVentaRepository detalleVentaRepository;
    private final VarianteProductoRepository varianteProductoRepository;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public VentaProducto generarVenta(VentaRequest venta) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario usuarioCreador = userRepository.findByEmail(email);
        VentaProducto ventaProducto = new VentaProducto();
        ventaProducto.setUsuario(usuarioCreador);
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
        log.info("Venta generada por : {}, detalles :{}", email, venta.getItems().size());
        return ventaProductoRepository.save(ventaProducto);
    }
}
