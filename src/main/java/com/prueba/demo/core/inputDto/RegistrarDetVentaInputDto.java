package com.prueba.demo.core.inputDto;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistrarDetVentaInputDto {
    
    private Integer idDetalleVenta;
    private Integer idVenta;
    private String tipoLadrillo;
    private Integer cantidadTotal;
    private BigDecimal precio;
    private String estado;

    private List<RegistrarDetProductoVentaInputDto> registrarProducto;
    //private List<RegistrarDetProductoVentaInputDto> listaProductoEliminados;
}
