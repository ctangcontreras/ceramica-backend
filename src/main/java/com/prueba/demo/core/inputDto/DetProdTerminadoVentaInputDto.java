package com.prueba.demo.core.inputDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetProdTerminadoVentaInputDto {

    private Integer idDetalleProductoTerminado;
    private Integer idProductoTerminado;
    private String codigoLadrillo;
    private String codigoEstado;
}
