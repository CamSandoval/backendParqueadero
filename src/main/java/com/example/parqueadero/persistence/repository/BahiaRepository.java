package com.example.parqueadero.persistence.repository;

import com.example.parqueadero.persistence.entity.bahia.Bahia;
import com.example.parqueadero.persistence.entity.vehiculo.TipoDeCarroceria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BahiaRepository extends JpaRepository<Bahia,Integer> {
    List<Bahia> findAllByOcupadoFalseAndTipo(TipoDeCarroceria tipoDeCarroceria);
}
