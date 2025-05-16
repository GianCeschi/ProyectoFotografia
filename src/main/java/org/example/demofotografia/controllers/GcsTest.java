package org.example.demofotografia.controllers;

import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.cloud.storage.Bucket;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class GcsTest implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        try {
            Storage storage = StorageOptions.getDefaultInstance().getService();
            // Obtener el nombre de un bucket para testear (puede ser uno que ya exista en tu proyecto)
            String bucketName = "example_bucket_gian";

            Bucket bucket = storage.get(bucketName);
            if (bucket != null) {
                System.out.println("✅ Conexión exitosa. El bucket existe: " + bucket.getName());
            } else {
                System.out.println("⚠️ El bucket no existe o no tenés permisos para verlo.");
            }

        } catch (Exception e) {
            System.err.println("❌ Error al conectarse a Google Cloud Storage:");
            e.printStackTrace();
        }
    }
}
