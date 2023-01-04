package com.prueba.demo.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.demo.core.inputDto.ProductoInicialInputDto;
import com.prueba.demo.core.model.ProductoInicial;
import com.prueba.demo.core.outputDto.ListaProductoInicialOutputDto;
import com.prueba.demo.mapper.ProductoInicialMapper;
import com.prueba.demo.service.ProductoInicialService;
import com.prueba.demo.support.dto.Respuesta;


@Service
public class ProductoInicialServiceImpl implements ProductoInicialService {
	private static final Logger log = LoggerFactory.getLogger(UsuarioServiceImpl.class);


	@Autowired
	private ProductoInicialMapper productoInicialMapper;
 
	@Override
	public Respuesta<?> getRegistrarProductoInicial(ProductoInicialInputDto param) throws Exception {

			ProductoInicial productoInicial = new ProductoInicial();
			productoInicial.setIdProductoInicial(param.getIdProductoInicial());
			productoInicial.setFechaRegistro(param.getFechaRegistro());
			productoInicial.setPrensa(param.getPrensa());
			productoInicial.setTipoLadrillo(param.getTipoLadrillo());
			productoInicial.setCantidadProducido(param.getCantidadProducido());
			productoInicial.setCantidadEstimada(param.getCantidadEstimada());
			productoInicial.setUsuarioCreacion(param.getUsuarioCreacion());
			productoInicial.setActivo(1);
			
			productoInicialMapper.getRegistrarProductoInicial(productoInicial);


			Respuesta resp = new Respuesta<>();
			resp.setSuccess(true);
            resp.setMessage("Se registraron correctamente los datos");
            return resp;
		
  }

  @Override
	public Respuesta<?> getListarProductoInicial(ProductoInicialInputDto param) throws Exception {
			ProductoInicial listProductoInicial = new ProductoInicial();
			listProductoInicial.setIdProductoInicial(param.getIdProductoInicial());
			listProductoInicial.setFechaRegistro(param.getFechaRegistro());
			listProductoInicial.setPrensa(param.getPrensa());
			listProductoInicial.setTipoLadrillo(param.getTipoLadrillo());
			List<ProductoInicial> listaProductoInicial = productoInicialMapper.getListarProductoInicial(listProductoInicial);

			List<ListaProductoInicialOutputDto> listaInicial = new ArrayList<>();
			if (!listaProductoInicial.isEmpty()) {
				ListaProductoInicialOutputDto lista = new ListaProductoInicialOutputDto();
			for (ProductoInicial element : listaProductoInicial) {
				lista = new ListaProductoInicialOutputDto();
				lista.setIdProductoInicial(element.getIdProductoInicial());
				lista.setFechaRegistroDesc(element.getFechaRegistroDesc());
				lista.setPrensaDesc(element.getPrensaDesc());
				lista.setCantidadProducido(element.getCantidadProducido());
				lista.setCantidadEstimada(element.getCantidadEstimada());
				lista.setActivo(1);
				lista.setDescripcionActivo(element.getDescripcionActivo());
				lista.setCCodigo(element.getCCodigo());
				lista.setCodigoProductoInicial(element.getCodigoProductoInicial());
				lista.setTipoLadrilloDesc(element.getTipoLadrilloDesc());
				listaInicial.add(lista);
			}

			Respuesta resp = new Respuesta<>();
			resp.setSuccess(true);
            resp.setMessage("Se listo correctamente los datos");
            resp.setDato(listaInicial);
            return resp;
		} else {	
			Respuesta resp = new Respuesta<>();
            resp.setSuccess(false);
            resp.setMessage("No se encontró registros");
            return resp;
		} 
  }

}