package com.example.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class TipoProducto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codigoProducto;
    private String nombreProducto;
    private String descripcionProducto;
    @OneToMany(mappedBy = "tipoProducto", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<VarianteProducto> varianteProductos = new HashSet<>();
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoria_id")
    private Categorias categoria;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "genero_id")
    private Generos genero;

    public void agregarVariante(VarianteProducto varianteProducto){
        varianteProductos.add(varianteProducto);
        varianteProducto.setTipoProducto(this);
    }

    public void removerVariante(VarianteProducto varianteProducto){
        varianteProductos.remove(varianteProducto);
        varianteProducto.setTipoProducto(null);
    }

}
