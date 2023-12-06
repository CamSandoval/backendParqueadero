package com.example.parqueadero.persistence.repository;

import com.example.parqueadero.persistence.entity.vehiculo.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VehiculoRepository extends ListCrudRepository<Vehiculo,Integer> {
    Vehiculo findByPlacaIgnoreCase(String placa);
}
