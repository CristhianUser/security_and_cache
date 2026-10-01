package com.example.api.agregates.requests;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class VarianteRequest {
    private String talla;
    private String color;
    private Integer stock;
    private Double precio;
    private MultipartFile foto1;
    private MultipartFile foto2;
    private MultipartFile foto3;
}
