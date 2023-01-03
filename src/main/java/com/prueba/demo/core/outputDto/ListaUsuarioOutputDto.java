package com.prueba.demo.core.outputDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListaUsuarioOutputDto {
    private Integer idUsuario;
    private Integer idSucursal;
    private Integer idPerfil;
    private String password;
    private String usuario;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
}
