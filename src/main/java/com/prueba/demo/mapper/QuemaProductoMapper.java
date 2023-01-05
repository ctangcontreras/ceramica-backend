package com.prueba.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.prueba.demo.core.model.QuemaProducto;

@Mapper
public interface QuemaProductoMapper {

    void  registrarQuemaProducto(QuemaProducto param);
    void  eliminarQuemaProducto(QuemaProducto param);
    List<QuemaProducto> listarQuemaProducto(QuemaProducto param);
}
