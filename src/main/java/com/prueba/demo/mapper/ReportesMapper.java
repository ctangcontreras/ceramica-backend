package com.prueba.demo.mapper;



import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.prueba.demo.core.model.ReporteProductoInicial;

@Mapper
public interface ReportesMapper {
   
   // void getReporteProductoInicial(ReporteProductoInicial param) throws Exception;
   List<ReporteProductoInicial>  getReporteProductoInicial(ReporteProductoInicial param);
}
