package com.prueba.demo.service;

import com.prueba.demo.core.inputDto.EliminarQuemaProductoInputDto;
import com.prueba.demo.core.inputDto.RegistrarQuemaProductoInputDto;
import com.prueba.demo.support.dto.Respuesta;

public interface QuemaProductoService {
    
    Respuesta<?> registarQuemaProducto(RegistrarQuemaProductoInputDto param) throws Exception;
    Respuesta<?> listarQuemaProducto(RegistrarQuemaProductoInputDto param) throws Exception;
    Respuesta<?> eliminarQuemaProducto(EliminarQuemaProductoInputDto param) throws Exception;
}
