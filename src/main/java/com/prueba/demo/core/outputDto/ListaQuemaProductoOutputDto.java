package com.prueba.demo.core.outputDto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ListaQuemaProductoOutputDto {

    private Integer idQuemaProducto;
    private Integer cantidadPaquete;
    private String codigo;


    private String descFechaRegistro;
    private String descHorno;
    private String fechaInicio;
    private String fechaFin;
    private String descripcionEstado;
}
