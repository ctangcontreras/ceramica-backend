package com.prueba.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.prueba.demo.core.model.QuemaProductoPersona;

@Mapper
public interface QuemaProductoPersonaMapper {
    
    void registrarQuemaProductoPersona(QuemaProductoPersona param);
    List<QuemaProductoPersona> listarQuemaProductoPersona(QuemaProductoPersona param);
}
