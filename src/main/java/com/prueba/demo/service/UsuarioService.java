package com.prueba.demo.service;
 

import com.prueba.demo.core.inputDto.UsuarioInputDto;
import com.prueba.demo.support.dto.Respuesta;
 

public interface UsuarioService {

	Respuesta<?> getListaUsuario(UsuarioInputDto param) throws Exception;
	
}
