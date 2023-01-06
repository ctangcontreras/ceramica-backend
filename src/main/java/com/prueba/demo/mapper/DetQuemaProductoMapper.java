package com.prueba.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.prueba.demo.core.model.DetalleQuemaProducto;

@Mapper
public interface DetQuemaProductoMapper {
    
    void registrarDetQuemaProducto(DetalleQuemaProducto param);
    List<DetalleQuemaProducto> listarDetQuemaProducto(DetalleQuemaProducto param);
}
