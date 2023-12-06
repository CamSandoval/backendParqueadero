package com.example.parqueadero.persistence.entity.vehiculo;

public record DTOVehiculoDetalles(Integer id,String placa,TipoDeCarroceria tipo,Boolean mensualidad) {
    public DTOVehiculoDetalles(Vehiculo vehiculo){
        this(vehiculo.getId(), vehiculo.getPlaca(), vehiculo.getTipo(),vehiculo.getMensualidad());
    }
}
