package org.example.demofotografia.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class Fotografia {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String url;

    @ElementCollection //Indica que nrosPlaca es una colección embebida en otra tabla.
    @CollectionTable(name = "fotografia_nros_placa", joinColumns = @JoinColumn(name = "fotografia_id"))
    @Column(name = "nro_placa") //@CollectionTable: Crea una tabla llamada fotografia_nros_placa con la clave foránea
    private List<String> nrosPlaca;

    // Constructores
    // Constructor sin parámetros -- CHEQUEAR PORQUE NO FUNCIONA NoArgsContructor
    public Fotografia() {
    }

    public Fotografia(String urlImagen, List<String> numerosPlaca) {
        this.url = urlImagen;
        this.nrosPlaca = numerosPlaca;
    }

    @Override
    public String toString() {
        return "Fotografia{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", nrosPlaca='" + nrosPlaca + '\'' +
                '}';
    }
}
