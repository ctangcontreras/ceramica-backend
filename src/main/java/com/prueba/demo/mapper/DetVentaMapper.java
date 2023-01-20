package com.prueba.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.prueba.demo.core.model.DetalleVenta;

@Mapper
public interface DetVentaMapper {
    
    void registrarDetVenta(DetalleVenta param);
    List<DetalleVenta> listarDetalleVenta(DetalleVenta param);
    void eliminarDetalleVenta(DetalleVenta param);
}
