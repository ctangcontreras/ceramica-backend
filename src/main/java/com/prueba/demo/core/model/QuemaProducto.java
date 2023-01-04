package com.prueba.demo.core.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class QuemaProducto extends ModeloBase{
    private Integer idQuemaProducto;
    private Date fechaRegistro;
    private String horno;
    private Integer cantidadPaquete;
    private String codigo;
}
