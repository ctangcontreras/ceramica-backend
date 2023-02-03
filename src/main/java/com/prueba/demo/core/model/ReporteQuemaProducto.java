package com.prueba.demo.core.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReporteQuemaProducto {
    
    private String fechaInicio;
    private String fechaFin;
    private List<ListaQuema> lista;
    private String logo;
    private String horno;

    @Setter
    @Getter
    public static class ListaQuema {
        private String fechaRegistro;
        private String horno;
        private String turno;
        private String cantidadPaquete;
        private String fechaInicioQuema;
        private String fechaFinQuema;
        private String observacion;
        private String personaEncargada;
        private String personaAyudante;
        private String personaAyudante2;
    
    }
    
    
}
