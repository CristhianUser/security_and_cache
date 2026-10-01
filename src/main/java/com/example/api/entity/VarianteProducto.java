package com.example.api.entity;

import com.example.api.agregates.requests.VarianteRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class VarianteProducto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String codigoVariante;
    @Column(unique = true)
    private String barras;
    private String talla;
    private String color;
    private Integer stock;
    private Double precio;
    private Boolean estado;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String foto1;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String foto2;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String foto3;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "producto_id")
    private TipoProducto tipoProducto;
}
