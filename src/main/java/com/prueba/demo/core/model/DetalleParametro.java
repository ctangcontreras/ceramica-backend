package com.prueba.demo.core.model;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DetalleParametro extends ModeloBase{
    
    private Integer idDetalleParametro;
    private Integer idParametro;
    private String codigo;
    private String descripcion;

    
}
