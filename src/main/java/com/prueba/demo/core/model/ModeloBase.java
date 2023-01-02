package com.prueba.demo.core.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModeloBase {
	private Integer activo;
	private String estado;
	private String usuarioCreacion;
	private String usuarioModificacion;
	private Date fechaCreacion;
	private Date fechaModificacion;
	private Integer totalRegistroPorPagina;
	private Integer pagina;
	private Integer totalPaginas;
	private Integer totalRegistros;
    
	@JsonIgnore
	private Integer resultado;
	@JsonIgnore
	private String mensaje;
	
	@JsonIgnore
	private String mensajeInterno;

}
