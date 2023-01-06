package com.prueba.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.prueba.demo.core.model.DetalleVenta;

@Mapper
public interface DetVentaMapper {
    
    void registrarDetVenta(DetalleVenta param);
}
