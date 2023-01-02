package com.prueba.demo.mapper;

import java.util.List;
import java.util.Map;

import com.prueba.demo.core.model.Persona;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PersonaMapper {
  List<Persona>  getListaPersona();
  Object  getListaPersonaProcedure(Map<String, Object> params); 
  
}
