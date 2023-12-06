package com.example.parqueadero.persistence.entity.registro;

import java.time.LocalDateTime;

public record DTORegistro (Integer id_vehiculo, Integer id_bahia, LocalDateTime hora_ingreso, LocalDateTime hora_salida,Double pago, Boolean activo){
    public DTORegistro(Registro registro){
        this(registro.getId_vehiculo(), registro.getId_bahia(),registro.getHora_ingreso(),registro.getHora_salida(),registro.getPago(),registro.getActivo());
    }
}
