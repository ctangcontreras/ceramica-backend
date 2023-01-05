package com.prueba.demo.core.outputDto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ListaQuemaProductoOutputDto {

    private Integer idQuemaProducto;
    private Integer cantidadPaquete;
    private String codigo;
    private Integer activo;
    private String horno;
    private Date fechaRegistro;
    private Date fechaInicio;
    private Date fechaFin;
    private String descFechaRegistro;
    private String descHorno;
    private String descFechaInicio;
    private String descFechaFin;
    private String descripcionEstado;
    private String ladoInicio;
    private String ladoFin;

}
