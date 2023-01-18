package com.prueba.demo.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prueba.demo.core.inputDto.DetProductoTerminadoInputDto;
import com.prueba.demo.core.inputDto.EliminarDetProductoTerminadoInputDto;
import com.prueba.demo.core.inputDto.EliminarProductoTerminadoInputDto;
import com.prueba.demo.core.inputDto.ProductoTerminadoInputDto;
import com.prueba.demo.core.model.DetalleProductoTerminado;
import com.prueba.demo.core.model.ProductoTerminado;
import com.prueba.demo.core.outputDto.ListaProductoTerminadoOutputDto;
import com.prueba.demo.core.outputDto.ListarDetProductoTerminadoOutputDto;
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
            //productoTerminado.setCrudo(Constantes.ESTADO_CRUDO);
            productoTerminado.setUsuarioCreacion(param.getUsuarioCreacion());
            productoTerminadoMapper.registrarProductoTerminado(productoTerminado);

            DetalleProductoTerminado detalle = new DetalleProductoTerminado();
            if (param.getListaDetallesEliminados() != null && !param.getListaDetallesEliminados().isEmpty()) {
                for (DetProductoTerminadoInputDto eliminados : param.getListaDetallesEliminados()) {
                    detalle.setIdDetalleProductoTerminado(eliminados.getIdDetalleProductoTerminado());
                    detalle.setUsuarioElimina(eliminados.getUsuarioElimina());
                    detProductoTerminadoMapper.eliminarDetProductoTerminado(detalle);
                }
            }


            if (param.getRegistrarDetalle() != null && !param.getRegistrarDetalle().isEmpty()) {
                for (DetProductoTerminadoInputDto element : param.getRegistrarDetalle()) {
                    detalle.setIdDetalleProductoTerminado(element.getIdDetalleProductoTerminado());
                    detalle.setIdProductoTerminado(productoTerminado.getIdProductoTerminado());
                    detalle.setNro(element.getNro());
                    detalle.setDescripcionTipoLadrillo(element.getTipoLadrillo());
                    detalle.setCantidadPaquete(element.getCantidadPaquete());
                    detalle.setCantidadCrudo(element.getCantidadCrudo());
                    detalle.setCrudo(element.getCrudo());
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

    @Override
    @Transactional
	public Respuesta<?> listarProductoTerminado(ProductoTerminadoInputDto param) throws Exception {
			ProductoTerminado productoTerminado = new ProductoTerminado();
			productoTerminado.setIdProductoTerminado(param.getIdProductoTerminado());
            productoTerminado.setFechaInicio(param.getFechaInicio());
            productoTerminado.setFechaFin(param.getFechaFin());
			productoTerminado.setHorno(param.getHorno());

			List<ProductoTerminado> listaProductoTerminado = productoTerminadoMapper.listarProductoTerminado(productoTerminado);

            List<ListaProductoTerminadoOutputDto> lista = new ArrayList<>();
         

            if (listaProductoTerminado != null && !listaProductoTerminado.isEmpty()) {
                ListaProductoTerminadoOutputDto e = new ListaProductoTerminadoOutputDto();
           
                for (ProductoTerminado element : listaProductoTerminado) {
                    e = new ListaProductoTerminadoOutputDto();
                    e.setIdProductoTerminado(element.getIdProductoTerminado());
                    e.setDescHorno(element.getDescHorno());
                    e.setHorno(element.getHorno());
                    e.setFechaRegistro(element.getFechaRegistro());
                    e.setDescFechaRegistro(element.getDescFechaRegistro());
                    e.setTotal(element.getTotal());
                    e.setPaquete(element.getPaquete());
                    //e.setDescStock(element.getDescStock());
                    e.setDescripcionActivo(element.getDescripcionActivo());
                    //e.setCrudo(element.getCrudo());
                    e.setActivo(element.getActivo());
                    e.setCodigo(element.getCodigo());
                    
                    DetalleProductoTerminado detalle = new DetalleProductoTerminado();
                    detalle.setIdProductoTerminado(element.getIdProductoTerminado());
                    List<DetalleProductoTerminado> listaDetalle = detProductoTerminadoMapper.listarDetProductoTerminado(detalle);

                    if (listaDetalle != null && !listaDetalle.isEmpty()) {
                        ListarDetProductoTerminadoOutputDto d = new ListarDetProductoTerminadoOutputDto();
                        List<ListarDetProductoTerminadoOutputDto> detalle2 = new ArrayList<>();
                        for (DetalleProductoTerminado element2 : listaDetalle) {
                            d = new ListarDetProductoTerminadoOutputDto();
                           
                            d.setIdDetalleProductoTerminado(element2.getIdDetalleProductoTerminado());
                            d.setIdProductoTerminado(element2.getIdProductoTerminado());
                            d.setNro(element2.getNro());
                            d.setCodigoLadrillo(element2.getCodigoLadrillo());
                            d.setDescripcionTipoLadrillo(element2.getDescripcionTipoLadrillo());
                            d.setCodigoEstado(element2.getCodigoEstado());
                            d.setDescripcionEstado(element2.getDescripcionEstado());
                            d.setDescripcionActivo(element2.getDescripcionActivo());
                            d.setActivo(element2.getActivo());
                            d.setCantidadCrudo(element2.getCantidadCrudo());
                            d.setCrudo(element2.getCrudo());
                            d.setTotal(element2.getTotal());
                            d.setCantidadPaquete(element2.getCantidadPaquete());
                            d.setIdVenta(element2.getIdVenta());
                            detalle2.add(d);    
                        }

                        e.setLista(detalle2);
                                        
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

  @Override
	public Respuesta<?> eliminarProductoTerminado(EliminarProductoTerminadoInputDto param) throws Exception {

        ProductoTerminado idProductoTerminado = new ProductoTerminado();
        idProductoTerminado.setIdProductoTerminado(param.getIdProductoTerminado());
        List<ProductoTerminado> listaProductoTerminado = productoTerminadoMapper.listarProductoTerminadoventa(idProductoTerminado);

        if (listaProductoTerminado != null && !listaProductoTerminado.isEmpty()) {
            Respuesta resp = new Respuesta<>();
            resp.setSuccess(false);
            resp.setMessage("No se puede eliminar el registro");
            return resp;
            
        }else{
            ProductoTerminado productoTerminado = new ProductoTerminado();
            productoTerminado.setIdProductoTerminado(param.getIdProductoTerminado());
            productoTerminado.setActivo(Constantes.ESTADO_INACTIVO);
            productoTerminado.setUsuarioElimina(param.getUsuarioElimina());
            productoTerminadoMapper.eliminarProductoTerminado(productoTerminado);

            Respuesta resp = new Respuesta<>();
            resp.setSuccess(productoTerminado.getResultado().equals(1)?true:false);
            resp.setMessage("Se eliminó el registro correctamente");
            return resp;
           
           
        }
        

  

 
       
		
  }

  @Override
	public Respuesta<?> eliminarDetProductoTerminado(EliminarDetProductoTerminadoInputDto param) throws Exception {

        DetalleProductoTerminado detProductoTerminado = new DetalleProductoTerminado();
        detProductoTerminado.setIdDetalleProductoTerminado(param.getIdDetalleProductoTerminado());
        detProductoTerminadoMapper.eliminarDetProductoTerminado(detProductoTerminado);

 
        Respuesta resp = new Respuesta<>();
        resp.setSuccess(detProductoTerminado.getResultado().equals(1)?true:false);
        resp.setMessage("Se eliminó el registro correctamente");
        return resp;
		
  }

}
