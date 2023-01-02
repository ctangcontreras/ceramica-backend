package com.prueba.demo.core.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Parametro extends ModeloBase{
    
    private Integer idParametro;
    private String codigo;
    private String descripcion;

    private List<Parametro> listaParametro = new ArrayList<>();
    
}
