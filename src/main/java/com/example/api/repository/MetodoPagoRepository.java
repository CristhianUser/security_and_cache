package com.example.api.repository;

import com.example.api.entity.MetodoPago;
import com.example.api.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MetodoPagoRepository extends JpaRepository<MetodoPago, Long> {
    MetodoPago findByIdAndByPagoUsuarioIdUser(Long idMetodo, Long idUser);
    List<MetodoPago> findByPagoUsuario(Usuario usuario);
}
