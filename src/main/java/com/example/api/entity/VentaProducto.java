package com.example.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
public class VentaProducto {
    @Id
    private String idVenta = UUID.randomUUID().toString().replace("-","").substring(0,10);
    @OneToMany(mappedBy = "ventaProducto", cascade = CascadeType.ALL)
    private Set<DetalleVenta> detalleVentas = new HashSet<>();
    private Double ventaTotal;
    @OneToOne
    @JoinColumn(name = "pago_id")
    private Pago pago;
    private Date fechaCreacion = new Date(System.currentTimeMillis());
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public void vincularDetalle(DetalleVenta detalleVenta){
        detalleVenta.setVentaProducto(this);
        detalleVentas.add(detalleVenta);
    }
}
