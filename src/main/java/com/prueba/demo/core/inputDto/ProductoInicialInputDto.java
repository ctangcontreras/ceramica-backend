package com.prueba.demo.core.inputDto;
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
    private String tipoLadrillo;
    private Integer cantidadProducido;
    private Integer cantidadEstimada;
    private Integer usuarioCreacion;
    private Date fechaInicio;
    private Date fechaFin;


}
