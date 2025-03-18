package org.example.demofotografia.controllers;

import org.example.demofotografia.entities.Fotografia;
import org.example.demofotografia.repositories.FotografiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/fotografias")
public class FotografiaController {

    @Autowired
    private FotografiaRepository fotografiaRepository;

    //NO FUNCIONA NO ME MUESTRAS LAS FOTOS QUE HAY EN LA BASE PERO ESTAN!
    @GetMapping("/todas")
    public List<Fotografia> obtenerTodas() {
        return fotografiaRepository.findAll();
    }

}
