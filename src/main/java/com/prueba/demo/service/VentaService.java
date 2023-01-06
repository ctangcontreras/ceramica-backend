package com.prueba.demo.service;

import com.prueba.demo.core.inputDto.RegistrarVentaInputDto;
import com.prueba.demo.support.dto.Respuesta;

public interface VentaService {

    Respuesta<?> registrarVenta(RegistrarVentaInputDto param) throws Exception;
    
}
