package com.prueba.demo.core.inputDto;

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
    private Integer cantidad;
    private String estado;

    private List<RegistrarDetProductoVentaInputDto> registrarProducto;
}
