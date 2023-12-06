package com.example.parqueadero.web.controller;

import com.example.parqueadero.persistence.entity.vehiculo.DTOCrearVehiculo;
import com.example.parqueadero.persistence.entity.vehiculo.DTOVehiculoDetalles;
import com.example.parqueadero.persistence.entity.vehiculo.Vehiculo;
import com.example.parqueadero.web.service.VehiculoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/vehiculos")
public class VehiculoController {
    @Autowired
    private VehiculoService service;

    @GetMapping("/{placa}")
    public ResponseEntity<DTOVehiculoDetalles> getVehiculo(@PathVariable String placa){
        Vehiculo vehiculo = service.getReferenceById(placa);
        return ResponseEntity.ok(new DTOVehiculoDetalles(vehiculo));
    }
    @PostMapping("/crear")
    @Transactional
    public ResponseEntity<DTOVehiculoDetalles> crearVehiculo(@Valid @RequestBody DTOCrearVehiculo data, UriComponentsBuilder builder){
        Vehiculo vehiculo = service.crearVehiculo(new Vehiculo(data));
        if(vehiculo.getId() == null || service.exists(vehiculo.getId())){
            DTOVehiculoDetalles vehiculoCreado =new DTOVehiculoDetalles(vehiculo);
            URI url = builder.path("/vehiculos/{placa}").buildAndExpand(vehiculo.getId()).toUri();
            return ResponseEntity.created(url).body(vehiculoCreado);
        }
        return ResponseEntity.badRequest().build();
    }
    @PutMapping("/mensualidad/activar/{placa}")
    @Transactional
    public ResponseEntity<DTOVehiculoDetalles> activarMensualidad(@PathVariable String placa){
        Vehiculo vehiculo =service.getReferenceById(placa);
        vehiculo.setMensualidad(true);
        return ResponseEntity.ok(new DTOVehiculoDetalles(vehiculo));
    }

    @PutMapping("/mensualidad/retirar/{placa}")
    @Transactional
    public ResponseEntity<DTOVehiculoDetalles> retirarMensualidad(@PathVariable String placa){
        Vehiculo vehiculo =service.getReferenceById(placa);
        vehiculo.setMensualidad(false);
        return ResponseEntity.ok(new DTOVehiculoDetalles(vehiculo));
    }
}
