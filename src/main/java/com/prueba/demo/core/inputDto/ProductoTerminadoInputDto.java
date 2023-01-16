package com.prueba.demo.core.inputDto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductoTerminadoInputDto {
    private Integer idProductoTerminado;
    private String horno;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "America/Lima")
    private Date fechaRegistro;
    private Integer paquete;
    private Integer total;
    private Integer activo;
    private Integer usuarioCreacion;
    //private Integer crudo;
    


    private List<DetProductoTerminadoInputDto> registrarDetalle;
    private List<DetProductoTerminadoInputDto> listaDetallesEliminados;
}
