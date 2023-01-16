package com.prueba.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.prueba.demo.core.model.DetalleProductoTerminado;

@Mapper
public interface DetProductoTerminadoMapper {
    
    void registrarDetProductoTerminado(DetalleProductoTerminado param);
    List<DetalleProductoTerminado> listarDetProductoTerminado(DetalleProductoTerminado param);
    List<DetalleProductoTerminado> listarDetProductoTerminadoVenta(DetalleProductoTerminado param);
    void eliminarDetProductoTerminado(DetalleProductoTerminado param);
}
