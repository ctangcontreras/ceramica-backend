package com.prueba.demo.core.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductoTerminado extends ModeloBase{
    private Integer idProductoTerminado;
    private String horno;
    private Date fechaRegistro;
    private Integer paquete;
    private Integer total;
    private Integer crudo;

    private String descFechaRegistro;
    private String descHorno;
    private String descContenido;
    
   
}
