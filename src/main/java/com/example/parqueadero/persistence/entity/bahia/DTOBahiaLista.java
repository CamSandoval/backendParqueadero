package com.example.parqueadero.persistence.entity.bahia;

import com.example.parqueadero.persistence.entity.vehiculo.TipoDeCarroceria;

public record DTOBahiaLista(Integer id, Integer numero, TipoDeCarroceria tipo) {
    public DTOBahiaLista(Bahia bahia) {
        this(bahia.getId(), bahia.getNumero(), bahia.getTipo());
    }
}
