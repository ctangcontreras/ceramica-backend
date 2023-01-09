package com.prueba.demo.core.outputDto;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ListaQuemaProductoOutputDto {

    private Integer idQuemaProducto;
    private Integer cantidadPaquete;
    private String observacion;
    private String codigo;
    private Integer activo;
    private String horno;
    private Date fechaRegistro;
    private String descFechaRegistro;
    private String descHorno;
    private String descFechaInicio;
    private String descFechaFin;
    private String descripcionEstado;

    private ListarDetQuemaProductoOutputDto inicio;
    private ListarDetQuemaProductoOutputDto fin;
    private List<ListarQuemaProductoPersonaOutputDto> listaPersona;

}
