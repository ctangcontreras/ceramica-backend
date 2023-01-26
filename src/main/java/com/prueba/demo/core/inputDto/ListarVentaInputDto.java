package com.prueba.demo.core.inputDto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListarVentaInputDto {

    private Integer idVenta;
    private String estadoVenta;
    private Date fechaInicio;
    private Date fechaFin;

    private String descEstadoVenta;
}
