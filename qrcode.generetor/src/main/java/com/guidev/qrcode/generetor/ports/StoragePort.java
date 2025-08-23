package com.guidev.qrcode.generetor.ports;

public interface StoragePort {
    String uploadFile(byte[] fileData, String fileName, String contentType);
}
