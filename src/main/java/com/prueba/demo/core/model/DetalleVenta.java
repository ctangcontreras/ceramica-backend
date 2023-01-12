package com.prueba.demo.core.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DetalleVenta extends ModeloBase{
    
    private Integer idDetalleVenta;
    private Integer idVenta;
    private String tipoLadrillo;
    private Integer cantidadTotal;
    private BigDecimal precio;
    private String estado;
    
}
