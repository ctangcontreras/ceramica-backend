package com.prueba.demo.service;
 

import com.prueba.demo.core.inputDto.ListarQuemaProductoInputDto;
import com.prueba.demo.core.inputDto.ProductoInicialInputDto;
import com.prueba.demo.core.inputDto.ProductoTerminadoInputDto;
import com.prueba.demo.support.dto.Respuesta;
 

public interface ReportesService {

	
	Respuesta<?> listarProductoInicialExcel(ProductoInicialInputDto param) throws Exception;
	Respuesta<?> listarProductoTerminadoExcel(ProductoTerminadoInputDto param) throws Exception;
	Respuesta<?> listarQuemaProductoExcel(ListarQuemaProductoInputDto param) throws Exception;
	
}
