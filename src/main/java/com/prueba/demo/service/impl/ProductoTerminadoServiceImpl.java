package com.prueba.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prueba.demo.core.inputDto.DetProductoTerminadoInputDto;
import com.prueba.demo.core.inputDto.ProductoTerminadoInputDto;
import com.prueba.demo.core.model.DetalleProductoTerminado;
import com.prueba.demo.core.model.ProductoTerminado;
import com.prueba.demo.mapper.DetProductoTerminadoMapper;
import com.prueba.demo.mapper.ProductoTerminadoMapper;
import com.prueba.demo.service.ProductoTerminadoService;
import com.prueba.demo.support.dto.Constantes;
import com.prueba.demo.support.dto.Respuesta;

@Service
public class ProductoTerminadoServiceImpl implements ProductoTerminadoService{
    
    @Autowired
    ProductoTerminadoMapper productoTerminadoMapper;

    @Autowired
    DetProductoTerminadoMapper detProductoTerminadoMapper;

    @Override
    @Transactional
	public Respuesta<?> registarProductoTerminado(ProductoTerminadoInputDto param) throws Exception {

            ProductoTerminado productoTerminado = new ProductoTerminado();
            productoTerminado.setIdProductoTerminado(param.getIdProductoTerminado());
            productoTerminado.setHorno(param.getHorno());
            productoTerminado.setFechaRegistro(param.getFechaRegistro());
            productoTerminado.setPaquete(param.getPaquete());
            productoTerminado.setTotal(param.getTotal());
            productoTerminado.setActivo(Constantes.ESTADO_ACTIVO);
            productoTerminado.setUsuarioCreacion(param.getUsuarioCreacion());
            productoTerminado.setCrudo(param.getCrudo());
            productoTerminadoMapper.registrarProductoTerminado(productoTerminado);

            DetalleProductoTerminado detalle = new DetalleProductoTerminado();

            if (param.getRegistrarDetalle() != null && !param.getRegistrarDetalle().isEmpty()) {
                for (DetProductoTerminadoInputDto element : param.getRegistrarDetalle()) {
                    detalle.setIdDetalleProductoTerminado(element.getIdDetalleProductoTerminado());
                    detalle.setIdProductoTerminado(productoTerminado.getIdProductoTerminado());
                    detalle.setNro(element.getNro());
                    detalle.setTipoLadrillo(element.getTipoLadrillo());
                    detalle.setCantidadPaquete(element.getCantidadPaquete());
                    detalle.setCantidadCrudo(element.getCantidadCrudo());
                    detalle.setTotal(element.getTotal());
                    detalle.setActivo(Constantes.ESTADO_ACTIVO);
                    detalle.setUsuarioCreacion(element.getUsuarioCreacion());
                    detalle.setEstado(element.getEstado());
                    detProductoTerminadoMapper.registrarDetProductoTerminado(detalle);
                }
            }
            
			Respuesta resp = new Respuesta<>();
			resp.setSuccess(productoTerminado.getResultado().equals(1)?true:false);
            resp.setMessage("Se registraron correctamente los datos");
            return resp;
		
  }

    /* @Override
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
 */
}
