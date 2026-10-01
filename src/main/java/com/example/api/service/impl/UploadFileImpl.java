package com.example.api.service.impl;

import com.example.api.service.UploadFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;
import java.util.UUID;

@Service
public class UploadFileImpl implements UploadFile {

    @Value("${app.upload.dir}")
    private String uploadFile;

    public static final String IMAGE_PATH = "/imagenes/productos/";

    @Override
    public String mapUrlBySaveFile(MultipartFile foto) throws IOException {

        if (foto == null || foto.isEmpty()) {
            return null;
        }

        Path folder = Paths.get(uploadFile);

        if(!Files.exists(folder)){
            Files.createDirectories(folder);
        }

        String fileOriginal = foto.getOriginalFilename();
        String extension = "";
        if(fileOriginal != null && fileOriginal.contains(".")){
            extension = fileOriginal.substring(fileOriginal.lastIndexOf("."));
        }

        String nombreImage = UUID.randomUUID().toString() + extension;
        Files.copy(foto.getInputStream(), folder.resolve(nombreImage), StandardCopyOption.REPLACE_EXISTING);
        return UrlPublica(nombreImage);
    }

    public String UrlPublica(String nombreArchivo){
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(IMAGE_PATH)
                .path(nombreArchivo)
                .toUriString();
    }
}
