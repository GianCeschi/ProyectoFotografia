package org.example.demofotografia.services;

import org.example.demofotografia.entities.Fotografia;
import org.example.demofotografia.repositories.FotografiaRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FotografiaService {
    @Autowired
    private FotografiaRepository fotografiaRepository;

    public void vincularImagenes(String requestJson, String responseJson) {
        JSONArray requestArray = new JSONObject(requestJson).getJSONArray("requests");
        JSONArray responseArray = new JSONObject(responseJson).getJSONArray("responses");

        // Iteramos hasta que no haya más imágenes o respuestas disponibles
        int cantidad = Math.min(requestArray.length(), responseArray.length());

        for (int i = 0; i < cantidad; i++) {
            String imageUrl = extraerUrlImagen(requestArray, i);
            String numeroPlaca = extraerNumeroPlaca(responseArray, i);
            //PODRIAMOS TENER UNA LISTA DE STRING (SEPARADOS POR /N)

            if (imageUrl != null && numeroPlaca != null) {
                Fotografia fotografia = new Fotografia(imageUrl, numeroPlaca);
                fotografiaRepository.save(fotografia);
            }
        }
    }

    private String extraerUrlImagen(JSONArray requestArray, int index) {
        try {
            return requestArray.getJSONObject(index)
                    .getJSONObject("image")
                    .getJSONObject("source")
                    .getString("gcsImageUri");
        } catch (Exception e) {
            return null;  // Si hay un error, devuelve null
        }
    }

    private String extraerNumeroPlaca(JSONArray responseArray, int index) {
        try {
            return responseArray.getJSONObject(index)
                    .getJSONArray("textAnnotations")
                    .getJSONObject(0)
                    .getString("description");
        } catch (Exception e) {
            return null;  // Si hay un error, devuelve null
        }
    }
}


