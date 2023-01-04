package com.prueba.demo.service;

import com.prueba.demo.core.inputDto.QuemaProductoInputDto;
import com.prueba.demo.support.dto.Respuesta;

public interface QuemaProductoService {
    
    Respuesta<?> registarQuemaProducto(QuemaProductoInputDto param) throws Exception;
}
