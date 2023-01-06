package com.prueba.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.prueba.demo.core.model.Venta;

@Mapper
public interface VentaMapper {
    
    void registrarVenta(Venta param);
}
