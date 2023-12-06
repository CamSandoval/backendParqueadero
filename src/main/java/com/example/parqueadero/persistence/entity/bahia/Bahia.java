package com.example.parqueadero.persistence.entity.bahia;

import com.example.parqueadero.persistence.entity.registro.Registro;
import com.example.parqueadero.persistence.entity.vehiculo.TipoDeCarroceria;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "bahia")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bahia {
    @Id
    @Column(name = "id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false,length = 2)
    private Integer numero;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoDeCarroceria tipo;

    @Column(nullable = false,columnDefinition = "TINYINT")
    private Boolean ocupado;

    @OneToMany(mappedBy = "bahia")
    private List<Registro> registros;

    public void changeOcupado() {
        this.ocupado=true;
    }

    public void liberar() {
        this.ocupado=false;
    }
}
