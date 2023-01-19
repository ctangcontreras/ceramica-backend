package com.prueba.demo.core.outputDto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ListarDetProductoVentaOuputDto {
    
    private Integer idDetalleProductoTerminado;
    private Integer idProductoTerminado;
    private String descripcionTipoLadrillo;
    private Integer cantidadPaquete;
    private Integer cantidadCrudo;
    private Integer crudo;
    private Integer total;
    private String descripcionEstado;
    private String codigo;
    private Integer utilizado;
}
