package com.example.api.service.impl;

import com.example.api.service.BarcodeService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
@RequiredArgsConstructor
public class BarcodeServiceImpl implements BarcodeService {


    @Override
    public byte[] generarCodigoBarras(String codeVariante, int ancho, int alto) {
        try {
            Code128Writer codigoBarra = new Code128Writer();
            BitMatrix bitMatrix = codigoBarra.encode(codeVariante, BarcodeFormat.CODE_128, ancho, alto);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);
            return outputStream.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
