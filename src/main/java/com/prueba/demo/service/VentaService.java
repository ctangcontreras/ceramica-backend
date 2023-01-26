package com.prueba.demo.service;

import com.prueba.demo.core.inputDto.DetProdTerminadoVentaInputDto;
import com.prueba.demo.core.inputDto.EliminarVentaInputDto;
import com.prueba.demo.core.inputDto.ListarVentaInputDto;
import com.prueba.demo.core.inputDto.RegistrarVentaInputDto;
import com.prueba.demo.support.dto.Respuesta;

public interface VentaService {

    Respuesta<?> registrarVenta(RegistrarVentaInputDto param) throws Exception;
    Respuesta<?> listarVenta(ListarVentaInputDto param) throws Exception;
    Respuesta<?> listarDetProductoVenta1(DetProdTerminadoVentaInputDto param) throws Exception;
    Respuesta<?> listarDetProductoVenta2(DetProdTerminadoVentaInputDto param) throws Exception;
    Respuesta<?> listarDetProductoVenta3(DetProdTerminadoVentaInputDto param) throws Exception;
    Respuesta<?> eliminarVenta(EliminarVentaInputDto param) throws Exception;
}
