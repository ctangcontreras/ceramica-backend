package com.prueba.demo.core.outputDto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ListaProductoTerminadoOutputDto {

    private Integer idProductoTerminado;
    private String descHorno;
    private String horno;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "America/Lima")
    private Date fechaRegistro;
    private Integer paquete;
    //private String descContenido;
   // private String descStock;
    //private String acciones;
    private Integer activo;
    private String descripcionActivo;
   private String descFechaRegistro;
    //private String descHorno;
    private Integer total;
    //private Integer crudo;
    private String codigo;

    private List<ListarDetProductoTerminadoOutputDto> lista;
    
   
   
    
    

    

}
