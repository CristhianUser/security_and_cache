package com.example.api.service;

public interface BarcodeService {
    byte[] generarCodigoBarras(String codeVariante, int ancho, int alto);
}
