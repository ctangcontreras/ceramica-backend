package com.prueba.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.prueba.demo.core.model.Venta;

@Mapper
public interface VentaMapper {
    
    void registrarVenta(Venta param);
    List<Venta> listarVenta(Venta param);
    void eliminarVenta(Venta param);
}
