package com.prueba.demo.core.outputDto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListaProductoInicialOutputDto {
    private Integer idProductoInicial;
    private Date fechaRegistro;
    private String prensa;
    private String tipoLadrillo;
    private Integer cantidadProducido;
    private Integer cantidadEstimada;
    private Integer activo;
    private String descripcionActivo;
    private String codigoProductoInicial;
    private String fechaRegistroDesc;
    private String tipoLadrilloDesc;
    private String prensaDesc;
    private String cCodigo;

    private String fechaInicioDesc;
    private String fechaFinDesc;
}
