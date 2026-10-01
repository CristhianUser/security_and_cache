package com.example.api.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${app.upload.dir}")
    private String uploadFile;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) { // 1. Corregido: sin 's' en Resource
        Path uploadPath = Paths.get(uploadFile).toAbsolutePath().normalize();

        registry.addResourceHandler("/imagenes/productos/**")
                .addResourceLocations("file:" + uploadPath.toString() + "/");
    }
}