package com.prueba.demo.core.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DetalleQuemaProducto extends ModeloBase{
    
    private Integer idDetalleQuemaProducto;
    private Integer idQuemaProducto;
    private String lado;
    private Date fechaDetalle;
    private Integer tipo;
}
