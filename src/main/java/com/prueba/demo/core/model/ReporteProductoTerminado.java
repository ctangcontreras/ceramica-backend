package com.prueba.demo.core.model;



import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReporteProductoTerminado {
    
    private String fechaInicio;
    private String fechaFin;
    private String horno;
    private String logo;
    private List<ListaProductoTerminado> lista;

    @Setter
    @Getter
    public static class ListaProductoTerminado {
        private String codigo;
        private String horno;
        private String fechaRegistro;
        private String paquete;
        private List<Contenido> contenido;
    }

    @Setter
    @Getter
    public static class Contenido {

        private String cantidadTotal;
        private String tipoLadrillo;
        private String estadoLadrillo;
    }
    

}
