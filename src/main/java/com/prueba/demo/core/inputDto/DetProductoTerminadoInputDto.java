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
public class DetProductoTerminadoInputDto {
    
    private Integer idDetalleProductoTerminado;
    private Integer idProductoTerminado;
    private Integer nro;
    private String tipoLadrillo;
    private String estado;
    private Integer cantidadPaquete;
    private Integer usuarioCreacion;
    private Integer cantidadCrudo;
    private Integer crudo;
    private Integer total;
    private Integer activo;
    private Integer usuarioElimina;
    
}
