package com.prueba.demo.core.inputDto;
import java.math.BigDecimal;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductoInicialInputDto {
    private Integer idProductoInicial;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "America/Lima")
    private Date fechaRegistro;
    private String prensa;
    private String prensaDesc;
    private String tipoLadrillo;
    private String tipoLadrilloDesc;
    private Integer cantidadProducido;
    private Integer cantidadEstimada;
    private Integer usuarioCreacion;
    private Date fechaInicio;
    private Date fechaFin;
    
    private BigDecimal pesoLadrillo;
    private Integer roturaAlambre;
    private String mezcla;
    private Integer amperaje;
    private String fallaMecanica;
    private String observaciones;

    private Boolean editarProductoInicial;


}
