package com.prueba.demo.core.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Usuario extends ModeloBase{
    
    private Integer idUsuario;
    private Integer idSucursal;
    private Integer idPerfil;
    private String password;
    private String usuario;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;

    private List<Usuario> listaUsuario = new ArrayList<>();
    
}
