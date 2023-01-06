package com.prueba.demo.mapper;



import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.prueba.demo.core.model.ProductoInicial;

@Mapper
public interface ProductoInicialMapper {
   
    void  getRegistrarProductoInicial(ProductoInicial param);
    List<ProductoInicial>  getListarProductoInicial(ProductoInicial param);
    void  eliminarProductoInicial(ProductoInicial param);
}
