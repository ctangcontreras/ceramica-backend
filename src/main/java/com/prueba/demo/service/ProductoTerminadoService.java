package com.prueba.demo.service;

import com.prueba.demo.core.inputDto.EliminarDetProductoTerminadoInputDto;
import com.prueba.demo.core.inputDto.EliminarProductoTerminadoInputDto;
import com.prueba.demo.core.inputDto.ProductoTerminadoInputDto;
import com.prueba.demo.support.dto.Respuesta;

public interface ProductoTerminadoService {
    
    Respuesta<?> registarProductoTerminado(ProductoTerminadoInputDto param) throws Exception;
    Respuesta<?> listarProductoTerminado(ProductoTerminadoInputDto param) throws Exception;
    Respuesta<?> eliminarProductoTerminado(EliminarProductoTerminadoInputDto param) throws Exception;
    Respuesta<?> eliminarDetProductoTerminado(EliminarDetProductoTerminadoInputDto param) throws Exception;
   
}
