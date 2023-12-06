package com.example.parqueadero.persistence.entity.registro;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DTOCreateRegistro(
    @NotNull
    Integer id_vehiculo,
    @NotNull
    Integer id_bahia
) {
}
