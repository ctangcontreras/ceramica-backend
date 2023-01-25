package com.prueba.demo.core.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReporteVenta {

    private String fechaInicio;
    private String fechaFin;
    private String logo;
    private List<ListaVenta> lista;

    @Setter
    @Getter
    public static class ListaVenta {

        private String codigo;
        private String fecha;
        private String tipoDocumento;
        private String numeroDocumento;
        private String descripcion;
        private String total;
        private String estado;
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
