package com.example.api.configuration;

import com.example.api.entity.*;
import com.example.api.repository.CategoriaRepository;
import com.example.api.repository.GeneroRepository;
import com.example.api.repository.MedioPagoRepository;
import com.example.api.repository.RolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class Initializer implements CommandLineRunner {

    private final RolRepository rolRepository;
    private final CategoriaRepository categoriaRepository;
    private final GeneroRepository generoRepository;
    private final MedioPagoRepository medioPagoRepository;

    @Override
    public void run(String... args) throws Exception {
        if(rolRepository.count() == 0){
            Rol rol_user = new Rol();
            Rol rol_admin = new Rol();
            rol_user.setNombreRol(Role.USER.name());
            rol_admin.setNombreRol(Role.ADMIN.name());
            Set<Rol> roles = new HashSet<>();
            roles.add(rol_user);
            roles.add(rol_admin);
            rolRepository.saveAll(roles);
            System.out.println("Roles creados correctamente");
        }

        if (categoriaRepository.count() == 0){
            Categorias c1 = new Categorias();
            c1.setNombreCategoria(CategoriasProducto.GORROS.name());
            Categorias c2 = new Categorias();
            c2.setNombreCategoria(CategoriasProducto.CAMISAS.name());
            Categorias c3 = new Categorias();
            c3.setNombreCategoria(CategoriasProducto.POLOS.name());
            Categorias c4 = new Categorias();
            c4.setNombreCategoria(CategoriasProducto.CASACAS.name());
            Categorias c5 = new Categorias();
            c5.setNombreCategoria(CategoriasProducto.BUZOS.name());
            Categorias c6 = new Categorias();
            c6.setNombreCategoria(CategoriasProducto.JEANS.name());
            Categorias c7 = new Categorias();
            c7.setNombreCategoria(CategoriasProducto.SHORT.name());
            Categorias c8 = new Categorias();
            c8.setNombreCategoria(CategoriasProducto.ZAPATILLAS.name());
            Categorias c9 = new Categorias();
            c9.setNombreCategoria(CategoriasProducto.ZAPATOS.name());
            Set<Categorias> categoriasRopa = new HashSet<>();
            categoriasRopa.add(c1);
            categoriasRopa.add(c2);
            categoriasRopa.add(c3);
            categoriasRopa.add(c4);
            categoriasRopa.add(c5);
            categoriasRopa.add(c6);
            categoriasRopa.add(c7);
            categoriasRopa.add(c8);
            categoriasRopa.add(c9);
            categoriaRepository.saveAll(categoriasRopa);
            System.out.println("Categorias creadas correctamente");
        }

        if (generoRepository.count() == 0){
            Generos g1 = new Generos();
            g1.setNombreGenero(TiposProducto.M.name());
            Generos g2 = new Generos();
            g2.setNombreGenero(TiposProducto.U.name());
            Generos g3 = new Generos();
            g3.setNombreGenero(TiposProducto.v.name());
            Set<Generos> generos = new HashSet<>();
            generos.add(g1);
            generos.add(g2);
            generos.add(g3);
            generoRepository.saveAll(generos);
            System.out.println("Generos creados correctamente");
        }

        if(medioPagoRepository.count() == 0){
            MedioPago medio1 = new MedioPago();
            medio1.setNombreMedio(MediosPago.BBVA.name());
            MedioPago medio2 = new MedioPago();
            medio2.setNombreMedio(MediosPago.BCP.name());
            MedioPago medio3 = new MedioPago();
            medio3.setNombreMedio(MediosPago.INTERBANK.name());
            MedioPago medio4 = new MedioPago();
            medio4.setNombreMedio(MediosPago.YAPE.name());
            MedioPago medio5 = new MedioPago();
            medio5.setNombreMedio(MediosPago.PLIN.name());
            Set<MedioPago> mediosPago = new HashSet<>();
            mediosPago.add(medio1);
            mediosPago.add(medio2);
            mediosPago.add(medio3);
            mediosPago.add(medio4);
            mediosPago.add(medio5);
            medioPagoRepository.saveAll(mediosPago);
            System.out.println("Medios de pago creados correctamente");
        }

    }
}
