package com.prueba.demo.core.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Venta extends ModeloBase{
    
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

}
