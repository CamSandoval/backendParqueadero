package com.example.parqueadero.web.service;

import com.example.parqueadero.persistence.entity.bahia.Bahia;
import com.example.parqueadero.persistence.entity.registro.Registro;
import com.example.parqueadero.persistence.entity.vehiculo.DTOVehiculoDetalles;
import com.example.parqueadero.persistence.repository.BahiaRepository;
import com.example.parqueadero.persistence.repository.RegistroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;

@Service
public class RegistroService {
    @Autowired
    private RegistroRepository repository;

    private final Double PRECIO_MINUTO = 80.55 ;

    public Registro getReferenceById(Integer id){
        return repository.findById(id).orElse(null);
    }

    public boolean exists(Integer idRegistro){
        return repository.existsById(idRegistro);
    }
    public Registro crearRegistro(Registro registro){
        return repository.save(registro);
    }

    public Boolean verificarRegistro(Integer idVehiculo){
        Integer resultado = repository.revisarActivo(idVehiculo);
        if (resultado==null){
            return false;
        }
        return resultado ==1 ;
    }

    public Registro salidaVehiculo(DTOVehiculoDetalles data){
        Registro registro = repository.buscarRegistro(data.id());
        registro.darSalida();
        long diferenciaDeMinutos = ChronoUnit.MINUTES.between(registro.getHora_ingreso(),registro.getHora_salida());
        System.out.println("Diferencia :  "+diferenciaDeMinutos);
        //eslablcer el pago
        if(!data.mensualidad()){
            switch (data.tipo()){
                case MOTO -> registro.setPago((PRECIO_MINUTO*0.5)*diferenciaDeMinutos);
                case SEDAN -> registro.setPago((PRECIO_MINUTO*0.8)*diferenciaDeMinutos);
                case  CAMIONETA, CAMION -> registro.setPago((PRECIO_MINUTO*1.2)*diferenciaDeMinutos);
                case  BUSETA -> registro.setPago((PRECIO_MINUTO*1.5)*diferenciaDeMinutos);
                case  BOLQUETA -> registro.setPago((PRECIO_MINUTO*1.8)*diferenciaDeMinutos);
            }
        }else{
            if(diferenciaDeMinutos<480){
                switch (data.tipo()){
                    case MOTO -> registro.setPago(((PRECIO_MINUTO*0.5)*diferenciaDeMinutos)*0.5);
                    case SEDAN -> registro.setPago(((PRECIO_MINUTO*0.8)*diferenciaDeMinutos)*0.5);
                    case  CAMIONETA, CAMION -> registro.setPago(((PRECIO_MINUTO*1.2)*diferenciaDeMinutos)*0.5);
                    case  BUSETA -> registro.setPago(((PRECIO_MINUTO*1.5)*diferenciaDeMinutos)*0.5);
                    case  BOLQUETA -> registro.setPago(((PRECIO_MINUTO*1.8)*diferenciaDeMinutos)*0.5);
                }
            }else{
                switch (data.tipo()){
                    case MOTO -> registro.setPago(((PRECIO_MINUTO*0.5)*diferenciaDeMinutos)*0.2);
                    case SEDAN -> registro.setPago(((PRECIO_MINUTO*0.8)*diferenciaDeMinutos)*0.2);
                    case  CAMIONETA, CAMION -> registro.setPago(((PRECIO_MINUTO*1.2)*diferenciaDeMinutos)*0.2);
                    case  BUSETA -> registro.setPago(((PRECIO_MINUTO*1.5)*diferenciaDeMinutos)*0.2);
                    case  BOLQUETA -> registro.setPago(((PRECIO_MINUTO*1.8)*diferenciaDeMinutos)*0.2);
                }
            }

        }

        return registro;
    }
}
