package org.example.demofotografia.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.*;

import org.springframework.web.util.UriComponentsBuilder;

@Service
public class VisionApiService {

    @Value("${vision.api.key}")
    private String apiKey;

    private static final String VISION_API_URL = "https://vision.googleapis.com/v1/images:annotate";

    public String procesarImagenes(String requestJson) {
        RestTemplate restTemplate = new RestTemplate();

        // Construcción correcta de la URL con el parámetro API key
        String url = UriComponentsBuilder.fromHttpUrl(VISION_API_URL)
                .queryParam("key", apiKey)
                .toUriString();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity = new HttpEntity<>(requestJson, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();  // Devuelve la respuesta JSON
        } else {
            return "Error en la API: " + response.getStatusCode() + " - " + response.getBody();
        }
    }
}

