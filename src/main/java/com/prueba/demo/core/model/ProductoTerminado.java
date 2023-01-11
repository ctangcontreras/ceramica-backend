package com.prueba.demo.core.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductoTerminado extends ModeloBase{
    private Integer idProductoTerminado;
    private String descHorno;
    private String horno;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "America/Lima")
    private Date fechaRegistro;
    private Integer paquete;
    private Integer total;
    private String codigo;
    private Integer crudo;
    private String acciones;
    private String descFechaRegistro;
    private String descStock;
    
   
}
