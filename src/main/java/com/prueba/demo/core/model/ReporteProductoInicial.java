package com.prueba.demo.core.model;



import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReporteProductoInicial extends ModeloBase{
    
    private Integer idProductoInicial;
    private String codigoProductoInicial;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "America/Lima")
    private Date fechaRegistro;
    private String fechaRegistroDesc;
    private String prensa;
    private String prensaDesc;
    private String tipoLadrilloDesc;
    private String tipoLadrillo;
    private Integer cantidadProducido;
    private Integer cantidadEstimada;
    private Integer diferencia;

    private Date fechaInicio;
    private Date fechaFin;

}
