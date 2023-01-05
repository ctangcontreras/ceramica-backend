package com.prueba.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.prueba.demo.core.model.ProductoTerminado;

@Mapper
public interface ProductoTerminadoMapper {

    void  registrarProductoTerminado(ProductoTerminado param);
    List<ProductoTerminado> listarProductoTerminado(ProductoTerminado param);
}
