package com.prueba.demo.core.outputDto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ListarDetVentaOutputDto {

    private Integer idDetalleVenta;
    private Integer idVenta;
    private String tipoLadrillo;
    private Integer cantidadTotal;
    private BigDecimal precio;
    private String estado;
    private Integer activo;
    private String descTipoLadrillo;
    private String descEstadoLadrillo;

    /* private List<ListarDetProductoVentaOuputDto> listaProductoVenta; */
    
}