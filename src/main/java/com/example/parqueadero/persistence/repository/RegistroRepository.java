package com.example.parqueadero.persistence.repository;

import com.example.parqueadero.persistence.entity.registro.Registro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroRepository extends ListCrudRepository<Registro,Integer> {

    @Query(value = """
            SELECT activo FROM registro WHERE id_vehiculo = :#{#idVehiculo} AND activo =1"""
            ,nativeQuery = true)
    Integer revisarActivo(@Param("idVehiculo") Integer idVehiculo);

    @Query(value = """
            SELECT * FROM registro WHERE id_vehiculo = :#{#idVehiculo} AND activo = 1"""
            ,nativeQuery = true)
    Registro buscarRegistro(@Param("idVehiculo") Integer idVehiculo);
}
