package com.prueba.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prueba.demo.core.inputDto.DetQuemaProductoInputDto;
import com.prueba.demo.core.inputDto.QuemaProductoInputDto;
import com.prueba.demo.core.model.DetalleQuemaProducto;
import com.prueba.demo.core.model.QuemaProducto;
import com.prueba.demo.mapper.DetQuemaProductoMapper;
import com.prueba.demo.mapper.QuemaProductoMapper;
import com.prueba.demo.service.QuemaProductoService;
import com.prueba.demo.support.dto.Constantes;
import com.prueba.demo.support.dto.Respuesta;

@Service
public class QuemaProductoServiceImpl implements QuemaProductoService{
    
    @Autowired
    QuemaProductoMapper quemaProductoMapper;

    @Autowired
    DetQuemaProductoMapper detQuemaProductoMapper;

    @Override
    @Transactional
	public Respuesta<?> registarQuemaProducto(QuemaProductoInputDto param) throws Exception {

            QuemaProducto quemaProducto = new QuemaProducto();
            quemaProducto.setIdQuemaProducto(param.getIdQuemaProducto());
            quemaProducto.setFechaRegistro(param.getFechaRegistro());
            quemaProducto.setHorno(param.getHorno());
            quemaProducto.setCantidadPaquete(param.getCantidadPaquete());
            quemaProducto.setCodigo(param.getCodigo());
            quemaProducto.setActivo(Constantes.ESTADO_ACTIVO);
            quemaProducto.setUsuarioCreacion(param.getUsuarioCreacion());
            quemaProductoMapper.registrarQuemaProducto(quemaProducto);

            DetalleQuemaProducto detalle = new DetalleQuemaProducto();

            if (param.getRegistrarDetalle() != null && !param.getRegistrarDetalle().isEmpty()) {
                for (DetQuemaProductoInputDto element : param.getRegistrarDetalle()) {
                    detalle.setIdDetalleQuemaProducto(element.getIdDetalleQuemaProducto());
                    detalle.setIdQuemaProducto(quemaProducto.getIdQuemaProducto());
                    detalle.setLado(element.getLado());
                    detalle.setFechaDetalle(element.getFechaDetalle());
                    detalle.setActivo(Constantes.ESTADO_ACTIVO);
                    detalle.setUsuarioCreacion(element.getUsuarioCreacion());
                    detQuemaProductoMapper.registrarDetQuemaProducto(detalle);
                }
            }
            
			Respuesta resp = new Respuesta<>();
			resp.setSuccess(quemaProducto.getResultado().equals(1)?true:false);
            resp.setMessage("Se registraron correctamente los datos");
            return resp;
		
  }

}
