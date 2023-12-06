package com.example.parqueadero.persistence.entity.vehiculo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DTOCrearVehiculo (
        @NotBlank
        @Pattern(regexp = "^[a-zA-Z0-9]{6}$")
        String placa,
        @NotNull(message = "El tipo de carroceria es obligatoria y debe estar diligenciada en mayusculas")
        TipoDeCarroceria tipo,
        @NotNull
        Boolean mensualidad
){
}
