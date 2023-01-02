package com.prueba.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.prueba.demo.core.model.DetalleParametro;
import com.prueba.demo.core.model.Parametro;

@Mapper
public interface ParametroMapper {
    public List<DetalleParametro> listDetParametro(Parametro param) throws Exception;
}
