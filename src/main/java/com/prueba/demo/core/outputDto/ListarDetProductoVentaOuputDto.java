package com.prueba.demo.core.outputDto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ListarDetProductoVentaOuputDto {
    
    private Integer idDetalleProductoVenta;
    private Integer idDetalleVenta;
    private Integer idDetProductoTerminado;
    private Integer utilizado;
    private Integer activo;
}
