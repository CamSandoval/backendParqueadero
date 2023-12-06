package com.example.parqueadero.persistence.entity.vehiculo;

import com.example.parqueadero.persistence.entity.registro.Registro;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "vehiculo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vehiculo {

    @Id
    @Column(name = "id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false,length = 6,unique = true)
    private String placa;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoDeCarroceria tipo;

    @Column(columnDefinition = "TINYINT",nullable = false)
    private Boolean mensualidad;

    @OneToMany(mappedBy = "vehiculo")
    private List<Registro> registros;

    public Vehiculo(DTOCrearVehiculo data) {
        this.placa=data.placa();
        this.tipo=data.tipo();
        this.mensualidad=data.mensualidad();
    }
}
