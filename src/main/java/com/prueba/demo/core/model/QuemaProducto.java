package com.prueba.demo.core.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class QuemaProducto extends ModeloBase{
    private Integer idQuemaProducto;
    private Date fechaRegistro;
    private String turno;
    private String horno;
    private Integer cantidadPaquete;
    private String codigo;
    private String observacion;

    private Date fechaInicio;
    private Date fechaFin;
    private String descFechaRegistro;
    private String descHorno;
    private String descFechaInicio;
    private String descFechaFin;
    private String descripcionEstado;
    private String descTurno;
}
