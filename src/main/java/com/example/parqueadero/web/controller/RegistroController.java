package com.example.parqueadero.web.controller;

import com.example.parqueadero.persistence.entity.bahia.Bahia;
import com.example.parqueadero.persistence.entity.registro.DTORegistro;
import com.example.parqueadero.persistence.entity.registro.DTOCreateRegistro;
import com.example.parqueadero.persistence.entity.registro.DTOValidarRegistro;
import com.example.parqueadero.persistence.entity.registro.Registro;
import com.example.parqueadero.persistence.entity.vehiculo.DTOVehiculoDetalles;
import com.example.parqueadero.persistence.repository.BahiaRepository;
import com.example.parqueadero.web.service.RegistroService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/registros")
public class RegistroController {

    @Autowired
    private RegistroService service;
    @Autowired
    private BahiaRepository bahiaRepository;

    @GetMapping("/{idVehiculo}")
    public ResponseEntity<DTOValidarRegistro> getregistro(@PathVariable Integer idVehiculo){
        Boolean tieneRegistroActivo = service.verificarRegistro(idVehiculo);
        return ResponseEntity.ok(new DTOValidarRegistro(tieneRegistroActivo));
    }
    @PostMapping("/crear")
    @Transactional
    public ResponseEntity<DTORegistro> crearRegistro(@Valid @RequestBody DTOCreateRegistro data, UriComponentsBuilder builder){
        if(!(service.verificarRegistro(data.id_vehiculo()))){
            Registro registro = service.crearRegistro(new Registro(data));
            System.out.println(data.id_bahia());
            Bahia bahia = bahiaRepository.getReferenceById(data.id_bahia());
            bahia.changeOcupado();
            if(registro.getId() == null || service.exists(registro.getId())){
                DTORegistro registroCreado =new DTORegistro(registro);
                URI url = builder.path("/registros/{idRegistro}").buildAndExpand(registro.getId()).toUri();
                return ResponseEntity.created(url).body(registroCreado);
            }
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/salidas")
    @Transactional
    public ResponseEntity<DTORegistro> salidaRegistro(@Valid @RequestBody DTOVehiculoDetalles data){
        if(service.verificarRegistro(data.id())){
            Registro registro= service.salidaVehiculo(data);
            Bahia bahia = bahiaRepository.getReferenceById(registro.getId_bahia());
            bahia.liberar();
            return ResponseEntity.ok(new DTORegistro(registro));
        }
        return ResponseEntity.badRequest().build();
    }
}
