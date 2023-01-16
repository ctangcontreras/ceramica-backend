package com.prueba.demo.core.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VentaRecojo extends ModeloBase{
    
    private Integer idVentaRecojo;
    private Integer idVenta;
    private Date fecha;
    private String observacion;
}
