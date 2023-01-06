package com.prueba.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.prueba.demo.core.model.DetalleProductoTerminado;

@Mapper
public interface DetProductoTerminadoMapper {
    
    void registrarDetProductoTerminado(DetalleProductoTerminado param);
    /* List<DetalleQuemaProducto> listarDetQuemaProducto(DetalleQuemaProducto param); */
}
