package com.prueba.demo.core.inputDto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetQuemaProductoInputDto {
    
    private Integer idDetalleQuemaProducto;
    private Integer idQuemaProducto;
    private String lado;
    /* @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm", timezone = "America/Lima") */
    private Date fechaDetalle;
    private Integer tipo;
    private Integer usuarioCreacion;
}
