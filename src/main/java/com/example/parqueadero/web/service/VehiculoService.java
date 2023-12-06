package com.example.parqueadero.web.service;

import com.example.parqueadero.persistence.entity.vehiculo.Vehiculo;
import com.example.parqueadero.persistence.repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehiculoService {
    @Autowired
    private VehiculoRepository repository;
    public Vehiculo getReferenceById(String placa) {
        return repository.findByPlacaIgnoreCase(placa);
    }

    public Vehiculo crearVehiculo(Vehiculo vehiculo){
        return repository.save(vehiculo);
    }
    public boolean exists(Integer id){
        return repository.existsById(id);
    }
}
