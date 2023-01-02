package com.prueba.demo.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.prueba.demo.core.model.Usuario;

@Mapper
public interface UsuarioMapper {
   
    List<Usuario>  getListaUsuario();
}
