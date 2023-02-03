package com.prueba.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prueba.demo.core.inputDto.RegistrarDetQuemaProductoInputDto;
import com.prueba.demo.core.inputDto.EliminarQuemaProductoInputDto;
import com.prueba.demo.core.inputDto.ListarQuemaProductoInputDto;
import com.prueba.demo.core.inputDto.RegistrarQuemaProductoInputDto;
import com.prueba.demo.core.inputDto.RegistrarQuemaProductoPersonaInputDto;
import com.prueba.demo.core.model.DetalleQuemaProducto;
import com.prueba.demo.core.model.QuemaProducto;
import com.prueba.demo.core.model.QuemaProductoPersona;
import com.prueba.demo.core.outputDto.ListaQuemaProductoOutputDto;
import com.prueba.demo.core.outputDto.ListarDetQuemaProductoOutputDto;
import com.prueba.demo.core.outputDto.ListarQuemaProductoPersonaOutputDto;
import com.prueba.demo.mapper.DetQuemaProductoMapper;
import com.prueba.demo.mapper.QuemaProductoMapper;
import com.prueba.demo.mapper.QuemaProductoPersonaMapper;
import com.prueba.demo.service.QuemaProductoService;
import com.prueba.demo.support.dto.Constantes;
import com.prueba.demo.support.dto.Respuesta;

@Service
public class QuemaProductoServiceImpl implements QuemaProductoService{
    
    @Autowired
    QuemaProductoMapper quemaProductoMapper;

    @Autowired
    DetQuemaProductoMapper detQuemaProductoMapper;

    @Autowired
    QuemaProductoPersonaMapper quemaProductoPersonaMapper;

    @Override
    @Transactional
	public Respuesta<?> registarQuemaProducto(RegistrarQuemaProductoInputDto param) throws Exception {

            QuemaProducto quemaProducto = new QuemaProducto();
            quemaProducto.setIdQuemaProducto(param.getIdQuemaProducto());
            quemaProducto.setFechaRegistro(param.getFechaRegistro());
            quemaProducto.setTurno(param.getTurno());
            quemaProducto.setHorno(param.getHorno());
            quemaProducto.setCantidadPaquete(param.getCantidadPaquete());
            quemaProducto.setObservacion(param.getObservacion());
            quemaProducto.setActivo(Constantes.ESTADO_ACTIVO);
            quemaProducto.setUsuarioCreacion(param.getUsuarioCreacion());
            quemaProductoMapper.registrarQuemaProducto(quemaProducto);

            DetalleQuemaProducto detalle = new DetalleQuemaProducto();

            if (param.getRegistrarDetalle() != null && !param.getRegistrarDetalle().isEmpty()) {
                for (RegistrarDetQuemaProductoInputDto element : param.getRegistrarDetalle()) {
                    detalle.setIdDetalleQuemaProducto(element.getIdDetalleQuemaProducto());
                    detalle.setIdQuemaProducto(quemaProducto.getIdQuemaProducto());
                    detalle.setLado(element.getLado());
                    detalle.setFechaDetalle(element.getFechaDetalle());
                    detalle.setTFechaDetalle(new java.sql.Timestamp(detalle.getFechaDetalle().getTime()));
                    detalle.setTipo(element.getTipo());
                    detalle.setActivo(Constantes.ESTADO_ACTIVO);
                    detalle.setUsuarioCreacion(param.getUsuarioCreacion());
                    detQuemaProductoMapper.registrarDetQuemaProducto(detalle);
                }
            }

            QuemaProductoPersona  persona = new QuemaProductoPersona();
            if (param.getRegistrarPersona() !=null && !param.getRegistrarPersona().isEmpty()) {
                for (RegistrarQuemaProductoPersonaInputDto element2 : param.getRegistrarPersona()) {
                     persona.setIdQuemaProductoPersona(element2.getIdQuemaProductoPersona());
                     persona.setIdQuemaProducto(quemaProducto.getIdQuemaProducto());
                     persona.setDni(element2.getDni());
                     persona.setApellidoPaterno(element2.getApellidoPaterno());
                     persona.setApellidoMaterno(element2.getApellidoMaterno());
                     persona.setNombres(element2.getNombres());
                     persona.setTipoPersona(element2.getTipoPersona());
                     persona.setActivo(Constantes.ESTADO_ACTIVO);
                     persona.setUsuarioCreacion(param.getUsuarioCreacion());
                     quemaProductoPersonaMapper.registrarQuemaProductoPersona(persona);
                }
            }
            
			Respuesta resp = new Respuesta<>();
			resp.setSuccess(quemaProducto.getResultado().equals(1)?true:false);
            resp.setMessage(param.getEditarQuema()?"Se actualizó correctamente el registro "+quemaProducto.getCodigo():
            "Se registró correctamente el registro "+quemaProducto.getCodigo());
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
	public Respuesta<?> listarQuemaProducto(ListarQuemaProductoInputDto param) throws Exception {
			QuemaProducto quemaProducto = new QuemaProducto();
			quemaProducto.setIdQuemaProducto(param.getIdQuemaProducto());
			quemaProducto.setFechaInicio(param.getFechaInicio());
            quemaProducto.setFechaFin(param.getFechaFin());
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
                    e.setObservacion(element.getObservacion());
                    e.setDescripcionEstado(element.getDescripcionEstado());
                    e.setCodigo(element.getCodigo());
                    e.setActivo(element.getActivo());
                    e.setTurno(element.getTurno());
                    e.setHorno(element.getHorno());
                    e.setFechaRegistro(element.getFechaRegistro());
                    e.setDescFechaInicio(element.getDescFechaInicio());
                    e.setDescFechaFin(element.getDescFechaFin());
                    e.setDescTurno(element.getDescTurno());

                    DetalleQuemaProducto detalle = new DetalleQuemaProducto();
                    detalle.setIdQuemaProducto(element.getIdQuemaProducto());
                    List<DetalleQuemaProducto> listaDetalle = detQuemaProductoMapper.listarDetQuemaProducto(detalle);

                    if (listaDetalle != null && !listaDetalle.isEmpty()) {
                        ListarDetQuemaProductoOutputDto d = new ListarDetQuemaProductoOutputDto();
                        for (DetalleQuemaProducto element2 : listaDetalle) {
                            d = new ListarDetQuemaProductoOutputDto();
                            if (element2.getTipo()==1) {
                                d.setIdDetalleQuemaProducto(element2.getIdDetalleQuemaProducto());
                                d.setIdQuemaProducto(element2.getIdQuemaProducto());
                                d.setLado(element2.getLado());
                                d.setDescLado(element2.getDescLado());
                                d.setActivo(element2.getActivo());
                                d.setFechaDetalle(element2.getFechaDetalle());
                                d.setTipo(element2.getTipo());
                                e.setInicio(d);
                            }

                            if (element2.getTipo()==2) {
                                d.setIdDetalleQuemaProducto(element2.getIdDetalleQuemaProducto());
                                d.setIdQuemaProducto(element2.getIdQuemaProducto());
                                d.setLado(element2.getLado());
                                d.setDescLado(element2.getDescLado());
                                d.setActivo(element2.getActivo());
                                d.setFechaDetalle(element2.getFechaDetalle());
                                d.setTipo(element2.getTipo());
                                e.setFin(d);
                            }
                        }
                    }
                    
                    QuemaProductoPersona quemaProductoPersona = new QuemaProductoPersona();
                    quemaProductoPersona.setIdQuemaProducto(element.getIdQuemaProducto());
                    List<QuemaProductoPersona> listaQuemaProductoPersona = quemaProductoPersonaMapper.listarQuemaProductoPersona(quemaProductoPersona);

                    List<ListarQuemaProductoPersonaOutputDto> listaPersona = new ArrayList<>();

                    if (listaQuemaProductoPersona != null && !listaQuemaProductoPersona.isEmpty()) {
                        ListarQuemaProductoPersonaOutputDto per = new ListarQuemaProductoPersonaOutputDto();
                        for (QuemaProductoPersona element3 : listaQuemaProductoPersona) {
                            per = new ListarQuemaProductoPersonaOutputDto();
                            per.setIdQuemaProductoPersona(element3.getIdQuemaProductoPersona());
                            per.setIdQuemaProducto(element3.getIdQuemaProducto());
                            per.setDni(element3.getDni());
                            per.setApellidoPaterno(element3.getApellidoPaterno());
                            per.setApellidoMaterno(element3.getApellidoMaterno());
                            per.setNombres(element3.getNombres());
                            per.setActivo(element3.getActivo());
                            per.setTipoPersona(element3.getTipoPersona());
                            listaPersona.add(per);
                        }

                        e.setListaPersona(listaPersona);
                    }
                    
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
