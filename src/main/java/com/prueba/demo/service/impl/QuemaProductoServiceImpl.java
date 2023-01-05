package com.prueba.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prueba.demo.core.inputDto.DetQuemaProductoInputDto;
import com.prueba.demo.core.inputDto.EliminarQuemaProductoInputDto;
import com.prueba.demo.core.inputDto.QuemaProductoInputDto;
import com.prueba.demo.core.model.DetalleQuemaProducto;
import com.prueba.demo.core.model.QuemaProducto;
import com.prueba.demo.core.outputDto.ListaQuemaProductoOutputDto;
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
                    detalle.setTipo(element.getTipo());
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

  @Override
	public Respuesta<?> eliminarQuemaProducto(EliminarQuemaProductoInputDto param) throws Exception {

        QuemaProducto quemaProducto = new QuemaProducto();
        quemaProducto.setIdQuemaProducto(param.getIdQuemaProducto());
        quemaProducto.setActivo(Constantes.ESTADO_INACTIVO);
        quemaProducto.setUsuarioElimina(param.getUsuarioElimina());
        quemaProductoMapper.eliminarQuemaProducto(quemaProducto);

 
        Respuesta resp = new Respuesta<>();
        resp.setSuccess(quemaProducto.getResultado().equals(1)?true:false);
        resp.setMessage("Se eliminó el registro correctamente");
        return resp;
		
  }

    @Override
    @Transactional
	public Respuesta<?> listarQuemaProducto(QuemaProductoInputDto param) throws Exception {
			QuemaProducto quemaProducto = new QuemaProducto();
			quemaProducto.setIdQuemaProducto(param.getIdQuemaProducto());
			quemaProducto.setFechaRegistro(param.getFechaRegistro());
			quemaProducto.setHorno(param.getHorno());

			List<QuemaProducto> listaQuemaProducto = quemaProductoMapper.listarQuemaProducto(quemaProducto);

            List<ListaQuemaProductoOutputDto> lista = new ArrayList<>();

            if (listaQuemaProducto != null && !listaQuemaProducto.isEmpty()) {
                ListaQuemaProductoOutputDto e = new ListaQuemaProductoOutputDto();
                for (QuemaProducto element : listaQuemaProducto) {
                    e = new ListaQuemaProductoOutputDto();
                    e.setIdQuemaProducto(element.getIdQuemaProducto());
                    e.setDescHorno(element.getDescHorno());
                    e.setDescFechaRegistro(element.getDescFechaRegistro());
                    e.setCantidadPaquete(element.getCantidadPaquete());
                    e.setFechaInicio(element.getFechaFin());
                    e.setFechaFin(element.getFechaFin());
                    e.setDescripcionEstado(element.getDescripcionEstado());
                    e.setCodigo(element.getCodigo());
                    e.setActivo(element.getActivo());
                    e.setHorno(element.getHorno());
                    e.setLadoInicio(element.getLadoInicio());
                    e.setLadoFin(element.getLadoFin());
                    e.setFechaRegistro(element.getFechaRegistro());
                    e.setDescFechaInicio(element.getDescFechaInicio());
                    e.setDescFechaFin(element.getDescFechaFin());
                    lista.add(e);
                }
                Respuesta resp = new Respuesta<>();
                resp.setSuccess(true);
                resp.setMessage("Se listó correctamente");
                resp.setDato(lista);
                return resp;
            } else {	
                Respuesta resp = new Respuesta<>();
                resp.setSuccess(false);
                resp.setMessage("No se encontró registros");
                return resp;
            } 
  }

}
