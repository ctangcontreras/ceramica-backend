package com.prueba.demo.core.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class QuemaProductoPersona extends ModeloBase{
    
    private Integer idQuemaProductoPersona;
    private Integer idQuemaProducto;
    private String dni;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String tipoPersona;
}
