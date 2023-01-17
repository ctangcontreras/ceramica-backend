package com.prueba.demo.core.inputDto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistrarVentaInputDto {
    
    private Integer idVenta;
    private Date fechaRegistro;
    private String tipoDocumento;
    private String metodoPago;
    private Integer pendienteRecojo;
    private Integer costoTotal;
    private String razonSocial;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String tipoVehiculo;
    private String placaVehiculo;
    private String estadoVenta;
    private String observacion;
    private Integer usuarioCreacion;

    private RegistrarVentaRecojoInputDto registrarVentaRecojo;
    private List<RegistrarDetVentaInputDto> registrarDetalle;
}
