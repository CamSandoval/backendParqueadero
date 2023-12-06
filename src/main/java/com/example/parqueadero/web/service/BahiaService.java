package com.example.parqueadero.web.service;

import com.example.parqueadero.persistence.entity.bahia.Bahia;
import com.example.parqueadero.persistence.entity.vehiculo.TipoDeCarroceria;
import com.example.parqueadero.persistence.repository.BahiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BahiaService {
    @Autowired
    private BahiaRepository repository;

    public List<Bahia> bahiasDisponibles(TipoDeCarroceria tipoDeCarroceria){
        return repository.findAllByOcupadoFalseAndTipo(tipoDeCarroceria);
    }
}
