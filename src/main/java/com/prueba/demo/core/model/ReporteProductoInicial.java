package com.prueba.demo.core.model;



import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReporteProductoInicial {
    
    private String fechaInicio;
    private String fechaFin;
    private String logo;
    private String prensa;
    private String tipoLadrillo;
    private List<ListaProductoInicial> listaProductoInicial;

    @Setter
    @Getter
    public static class ListaProductoInicial {
        private String codigo;
        private String fechaRegistro;
        private String prensa;
        private String tipoLadrillo;
        private Integer cantidadProducida;
        private Integer cantidadEstimada;
        private Integer diferencia;

   
    
    }
    

}
