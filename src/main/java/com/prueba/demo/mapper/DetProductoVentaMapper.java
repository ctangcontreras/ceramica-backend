package com.prueba.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.prueba.demo.core.model.DetalleProductoVenta;

@Mapper
public interface DetProductoVentaMapper {
    
    void registrarDetProductoVenta(DetalleProductoVenta param);
}
