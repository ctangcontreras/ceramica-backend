package com.prueba.demo.core.outputDto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ListarDetQuemaProductoOutputDto {
    
    private Integer idDetalleQuemaProducto;
    private Integer idQuemaProducto;
    private String lado;
    private Date fechaDetalle;
    private Integer tipo;
    private Integer activo;
    private String descLado;
}
