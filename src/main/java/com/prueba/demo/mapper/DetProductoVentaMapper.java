package com.prueba.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.prueba.demo.core.model.DetalleProductoTerminado;
import com.prueba.demo.core.model.DetalleProductoVenta;

@Mapper
public interface DetProductoVentaMapper {
    
    void registrarDetProductoVenta(DetalleProductoVenta param);
    List<DetalleProductoVenta> listarDetalleProductoVenta1(DetalleProductoVenta param);
    List<DetalleProductoTerminado> listarDetalleProductoVenta2(DetalleProductoTerminado param);
    void eliminarDetalleProductoVenta(DetalleProductoVenta param);
}
