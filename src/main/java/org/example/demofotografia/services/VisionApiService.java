package org.example.demofotografia.services;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.*;

@Service
public class VisionApiService {

    private static final String API_KEY = "AIzaSyBN1S3Q3YHALrcUPJyuB4LKnNO_huiawbs"; //NO EXPONERLA ACA
    private static final String VISION_URL = "https://vision.googleapis.com/v1/images:annotate?key=" + API_KEY;

    public String procesarImagenes(String requestJson) {
        RestTemplate restTemplate = new RestTemplate();
        //RestTemplate → Es una clase de Spring Boot que facilita el envío de peticiones HTTP y
        // el manejo de respuestas.

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        //HttpHeaders → Se usa para definir los encabezados de la petición.
        //headers.setContentType(MediaType.APPLICATION_JSON); → Especifica que el contenido de la solicitud
        // será JSON.

        HttpEntity<String> requestEntity = new HttpEntity<>(requestJson, headers);

        ResponseEntity<String> response = restTemplate.exchange(VISION_URL, HttpMethod.POST, requestEntity, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();  // Devuelve la respuesta JSON
        } else {
            return "Error en la API: " + response.getStatusCode() + " - " + response.getBody();
        }
    }

}
