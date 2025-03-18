package org.example.demofotografia.services;

import org.example.demofotografia.repositories.FotografiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.example.demofotografia.repositories.FotografiaRepository;
import org.example.demofotografia.entities.Fotografia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.json.JSONObject;

@Service
public class FotografiaService {
    @Autowired
    private FotografiaRepository fotografiaRepository;

    public void vincularImagenes(String requestJson, String responseJson) {
        // Extraemos la URL de la imagen del request
        String imageUrl = extraerUrlImagen(requestJson); //PASAR INDICE Y HACER FOR

        // Extraemos el número de placa del response
        String numeroPlaca = extraerNumeroPlaca(responseJson);

        // Guardamos en la base de datos si ambos valores son válidos
        if (imageUrl != null && numeroPlaca != null) {
            Fotografia fotografia = new Fotografia(imageUrl, numeroPlaca);
            fotografiaRepository.save(fotografia); // Guardar en la BD
        }
    }

    private String extraerUrlImagen(String requestJson) {
        try {
            JSONObject jsonObject = new JSONObject(requestJson);
            return jsonObject.getJSONArray("requests")
                    .getJSONObject(0)
                    .getJSONObject("image")
                    .getJSONObject("source")
                    .getString("gcsImageUri");
        } catch (Exception e) {
            return null;
        }
    }

    private String extraerNumeroPlaca(String responseJson) {
        try {
            JSONObject jsonObject = new JSONObject(responseJson);
            return jsonObject.getJSONArray("responses")
                    .getJSONObject(0)
                    .getJSONArray("textAnnotations")
                    .getJSONObject(0)
                    .getString("description");
        } catch (Exception e) {
            return null;
        }
    }
}

