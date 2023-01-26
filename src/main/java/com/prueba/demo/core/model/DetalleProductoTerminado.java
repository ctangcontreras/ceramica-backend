package com.prueba.demo.core.model;



import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DetalleProductoTerminado extends ModeloBase{
    
    private Integer idDetalleProductoTerminado;
    private Integer idProductoTerminado;
    private String codigoLadrillo;
    private String descripcionTipoLadrillo;
    private String codigoEstado;
    private String descripcionEstado;
    private Integer cantidadPaquete;
    private Integer cantidadCrudo;
    private Integer crudo;
    private Integer total;
    private Integer utilizado;
    private Integer idVenta;

    private String codigo;
    private Integer idDetalleVenta;
    
}
