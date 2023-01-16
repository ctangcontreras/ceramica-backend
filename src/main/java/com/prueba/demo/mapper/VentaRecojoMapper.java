package com.prueba.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.prueba.demo.core.model.VentaRecojo;

@Mapper
public interface VentaRecojoMapper {
    
    void registrarVentaRecojo(VentaRecojo param);
}
