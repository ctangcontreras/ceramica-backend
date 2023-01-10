package com.prueba.demo.core.outputDto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ListarQuemaProductoPersonaOutputDto {
    
    private Integer idQuemaProductoPersona;
    private Integer idQuemaProducto;
    private String dni;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String tipoPersona;
    private Integer activo;
}
