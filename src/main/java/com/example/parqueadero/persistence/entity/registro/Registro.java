package com.example.parqueadero.persistence.entity.registro;

import com.example.parqueadero.persistence.entity.bahia.Bahia;
import com.example.parqueadero.persistence.entity.vehiculo.Vehiculo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "registro")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Registro {

    @Id
    @Column(name = "id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "id_vehiculo",nullable = false)
    private Integer id_vehiculo;

    @Column(name = "id_bahia",nullable = false)
    private Integer id_bahia;

    @Column(nullable = false,columnDefinition = "DATETIME")
    private LocalDateTime hora_ingreso;

    @Column(columnDefinition = "DATETIME")
    private LocalDateTime hora_salida;

    @Column(columnDefinition = "DECIMAL")
    private Double pago;

    @Column(nullable = false,columnDefinition = "TINYINT")
    private Boolean activo;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_bahia",referencedColumnName = "id",insertable = false,updatable = false)
    private Bahia bahia;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_vehiculo",referencedColumnName = "id",insertable = false,updatable = false)
    private Vehiculo vehiculo;

    public Registro(DTOCreateRegistro data) {
        this.id_vehiculo=data.id_vehiculo();
        this.id_bahia= data.id_bahia();
        this.hora_ingreso=LocalDateTime.now();
        this.activo=true;
    }

    public void darSalida() {
        this.hora_salida=LocalDateTime.now();
        this.activo=false;
    }
}
