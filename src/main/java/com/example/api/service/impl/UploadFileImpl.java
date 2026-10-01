package com.example.api.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.api.service.UploadFile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UploadFileImpl implements UploadFile {

    private final Cloudinary cloudinary;

    @Override
    public String mapUrlBySaveFile(MultipartFile foto) throws IOException {

        if (foto == null || foto.isEmpty()) {
            return null;
        }

        Map upload = cloudinary.uploader().upload(foto.getBytes(), ObjectUtils.emptyMap());

        return upload.get("secure_url").toString();
    }
}
