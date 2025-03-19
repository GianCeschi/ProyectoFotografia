package org.example.demofotografia.services;

import org.example.demofotografia.entities.Fotografia;
import org.example.demofotografia.repositories.FotografiaRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
            List<String> numeroPlaca = extraerNumerosPlaca(responseArray, i);

            if (imageUrl != null && !numeroPlaca.isEmpty()) {
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

    private List<String> extraerNumerosPlaca(JSONArray responseArray, int index) {
        List<String> numerosPlaca = new ArrayList<>();
        try {
            JSONObject responseObject = responseArray.getJSONObject(index);
            // Verificar si 'textAnnotations' existe en la respuesta
            if (responseObject.has("textAnnotations")) { //: Se verifica si la clave textAnnotations existe dentro de responseObject. Si existe, devuelve true; Tuve que agregar porque me daba error sino
                System.out.println("ENTRO ACA, SI NO HAY MAS IMAGENES NO TIENE QUE ENTRAR MAS");

                String textoPlaca = responseObject.getJSONArray("textAnnotations")
                        .getJSONObject(0)
                        .getString("description");

                String[] lineas = textoPlaca.split("\n");

                Pattern pattern = Pattern.compile("\\d+");

                for (String linea : lineas) {
                    Matcher matcher = pattern.matcher(linea);
                    while (matcher.find()) {  // Busca números dentro de la línea
                        numerosPlaca.add(matcher.group());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return numerosPlaca;
    }


}


