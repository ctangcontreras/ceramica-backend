package com.prueba.demo.core.model;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Parametro extends ModeloBase{
    
    private Integer idParametro;
    private String codigo;
    private String descripcion;

    
}
