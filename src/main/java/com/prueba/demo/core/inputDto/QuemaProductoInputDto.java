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
public class QuemaProductoInputDto {
    private Integer idQuemaProducto;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "America/Lima")
    private Date fechaRegistro;
    private String horno;
    private Integer cantidadPaquete;
    private String codigo;
    private Integer usuarioCreacion;

    private List<DetQuemaProductoInputDto> registrarDetalle;
}
