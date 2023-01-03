package com.prueba.demo.service;
 

import com.prueba.demo.core.inputDto.ProductoInicialInputDto;
import com.prueba.demo.support.dto.Respuesta;
 

public interface ProductoInicialService {

	Respuesta<?> getRegistrarProductoInicial(ProductoInicialInputDto param) throws Exception;
	Respuesta<?> getListarProductoInicial(ProductoInicialInputDto param) throws Exception;
	
}
