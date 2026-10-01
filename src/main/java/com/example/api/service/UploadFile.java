package com.example.api.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadFile {
    String mapUrlBySaveFile(MultipartFile foto) throws IOException;
}
