package org.example.demofotografia.controllers;

import org.example.demofotografia.services.FotografiaService;
import org.example.demofotografia.services.VisionApiService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/vision")
public class VisionApiController {

    @Autowired
    private VisionApiService visionApiService;
    @Autowired
    private FotografiaService fotografiaService;

    @PostMapping("/procesar")
    public ResponseEntity<String> procesarImagen(@RequestBody String requestJson) {
        // Enviamos la imagen a la API de Google Vision
        String responseJson = visionApiService.procesarImagenes(requestJson);

        // Vinculamos request con response en FotografiaService
        fotografiaService.vincularImagenes(requestJson, responseJson);

        return ResponseEntity.ok(responseJson);
    }
}

