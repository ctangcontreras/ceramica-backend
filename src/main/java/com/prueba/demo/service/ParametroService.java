package com.prueba.demo.service;

import com.prueba.demo.support.dto.Respuesta;

public interface ParametroService {

    public Respuesta<?> listDetParametro(String codigo) throws Exception;
}
