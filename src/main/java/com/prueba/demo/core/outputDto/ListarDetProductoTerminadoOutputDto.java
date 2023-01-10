package com.prueba.demo.core.outputDto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ListarDetProductoTerminadoOutputDto {
    
    private Integer idDetalleProductoTerminado;
    private Integer idProductoTerminado;
    private Integer nro;
    private String codigoLadrillo;
    private String descripccionTipoLadrillo;
    private String codigoEstado;
    private String descripcionEstado;
    private Integer cantidadPaquete;
    private String descripcionActivo;
    private Integer activo;
    private Integer cantidadCrudo;
    private Integer crudo;
    private Integer total;
    
}
