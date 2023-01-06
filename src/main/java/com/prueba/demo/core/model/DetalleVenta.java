package com.prueba.demo.core.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DetalleVenta extends ModeloBase{
    
    private Integer idDetalleVenta;
    private Integer idVenta;
    private String tipoLadrillo;
    private Integer cantidad;
    private String estado;
    
}
