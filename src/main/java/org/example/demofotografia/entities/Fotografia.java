package org.example.demofotografia.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
public class Fotografia {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String url;

    @Column
    private String nroPlaca;

    // Constructores
    // Constructor sin parámetros -- CHEQUEAR PORQUE NO FUNCIONA NoArgsContructor
    public Fotografia() {
    }

    public Fotografia(String urlImagen, String numeroPlaca) {
        this.url = urlImagen;
        this.nroPlaca = numeroPlaca;
    }

    @Override
    public String toString() {
        return "Fotografia{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", nroPlaca='" + nroPlaca + '\'' +
                '}';
    }
}
