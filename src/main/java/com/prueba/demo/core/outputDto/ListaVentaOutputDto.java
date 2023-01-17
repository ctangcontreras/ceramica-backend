package com.prueba.demo.core.outputDto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ListaVentaOutputDto {

    private Integer idVenta;
    private Date fechaRegistro;
    private String tipoDocumento;
    private String numeroDocumento;
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
    private String codigo;
    private Integer activo;
    private String descripcionActivo;
    private String descFechaRegistro;
    private String descTipoDocumento1;
    private String descTipoDocumento2;
}
