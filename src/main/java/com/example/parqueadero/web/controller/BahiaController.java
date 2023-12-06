package com.example.parqueadero.web.controller;

import com.example.parqueadero.persistence.entity.bahia.DTOBahiaLista;
import com.example.parqueadero.persistence.entity.vehiculo.TipoDeCarroceria;
import com.example.parqueadero.web.service.BahiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bahias")
public class BahiaController {
    @Autowired
    private BahiaService service;

    @GetMapping("/disponibles/{tipoCarroceria}")
    public ResponseEntity<List<DTOBahiaLista>> getBahias(@PathVariable TipoDeCarroceria tipoCarroceria){
        return ResponseEntity.ok(service.bahiasDisponibles(tipoCarroceria).stream().map(DTOBahiaLista::new).collect(Collectors.toList()));
    }

}
