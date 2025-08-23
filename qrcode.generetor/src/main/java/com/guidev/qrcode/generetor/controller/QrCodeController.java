package com.guidev.qrcode.generetor.controller;


import com.guidev.qrcode.generetor.dito.QrCodeGeneratorRequest;
import com.guidev.qrcode.generetor.dito.QrCodeGeneratorResponse;
import com.guidev.qrcode.generetor.services.QrCodeGeneratorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/qrcode")
public class QrCodeController {

    private final QrCodeGeneratorService qrCodeGeneratorService;

    public QrCodeController(QrCodeGeneratorService qrCodeGeneratorService) {
        this.qrCodeGeneratorService = qrCodeGeneratorService;
    }

    @PostMapping
    public ResponseEntity<QrCodeGeneratorResponse> generate(@RequestBody QrCodeGeneratorRequest request){
            try {
                QrCodeGeneratorResponse response = this.qrCodeGeneratorService.generateAndUploadQrCode(request.text());
                        return ResponseEntity.ok(response);
            } catch (Exception e) {
                System.out.println(e);
                return ResponseEntity.internalServerError().build();
            }
    }
}
