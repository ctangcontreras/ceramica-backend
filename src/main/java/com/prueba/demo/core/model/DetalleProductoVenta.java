package com.prueba.demo.core.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DetalleProductoVenta extends ModeloBase{
    
    private Integer idDetalleProductoVenta;
    private Integer idDetalleVenta;
    private Integer idDetProductoTerminado;
    private Integer utilizado;
    
}
