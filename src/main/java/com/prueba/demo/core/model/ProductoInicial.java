package com.prueba.demo.core.model;



import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductoInicial extends ModeloBase{
    
    private Integer idProductoInicial;
    private Date fechaRegistro;
    private String prensa;
    private String tipoLadrillo;
    private Integer cantidadProducido;
    private Integer cantidadEstimada;

    private String fechaRegistroDesc;
    private String tipoLadrilloDesc;
    private String prensaDesc;
    private String cCodigo;
}