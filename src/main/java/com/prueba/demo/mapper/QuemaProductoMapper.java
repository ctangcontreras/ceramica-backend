package com.prueba.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.prueba.demo.core.model.QuemaProducto;

@Mapper
public interface QuemaProductoMapper {

    void  registrarQuemaProducto(QuemaProducto param);
    
}
