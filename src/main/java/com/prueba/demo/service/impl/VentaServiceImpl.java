package com.prueba.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prueba.demo.core.inputDto.DetProdTerminadoVentaInputDto;
import com.prueba.demo.core.inputDto.ListarVentaInputDto;
import com.prueba.demo.core.inputDto.RegistrarDetProductoVentaInputDto;
import com.prueba.demo.core.inputDto.RegistrarDetVentaInputDto;
import com.prueba.demo.core.inputDto.RegistrarVentaInputDto;
import com.prueba.demo.core.model.DetalleProductoTerminado;
import com.prueba.demo.core.model.DetalleProductoVenta;
import com.prueba.demo.core.model.DetalleVenta;
import com.prueba.demo.core.model.Venta;
import com.prueba.demo.core.model.VentaRecojo;
import com.prueba.demo.core.outputDto.DetProdTerminadoVentaOuputDto;
import com.prueba.demo.core.outputDto.ListaVentaOutputDto;
import com.prueba.demo.core.outputDto.ListarDetVentaOutputDto;
import com.prueba.demo.mapper.DetProductoTerminadoMapper;
import com.prueba.demo.mapper.DetProductoVentaMapper;
import com.prueba.demo.mapper.DetVentaMapper;
import com.prueba.demo.mapper.VentaMapper;
import com.prueba.demo.mapper.VentaRecojoMapper;
import com.prueba.demo.service.VentaService;
import com.prueba.demo.support.dto.Constantes;
import com.prueba.demo.support.dto.Respuesta;

@Service
public class VentaServiceImpl implements VentaService{
    
    @Autowired
    VentaMapper ventaMapper;

    @Autowired
    DetVentaMapper detVentaMapper;

    @Autowired
    DetProductoVentaMapper detProductoVentaMapper;

    @Autowired
    DetProductoTerminadoMapper detProductoTerminadoMapper;

    @Autowired
    VentaRecojoMapper ventaRecojoMapper;

    @Override
    @Transactional
	public Respuesta<?> registrarVenta(RegistrarVentaInputDto param) throws Exception {

            Venta venta = new Venta();
            venta.setIdVenta(param.getIdVenta());
            venta.setFechaRegistro(param.getFechaRegistro());
            venta.setTipoDocumento(param.getTipoDocumento());
            venta.setNumeroDocumento(param.getNumeroDocumento());
            venta.setMetodoPago(param.getMetodoPago());
            venta.setPendienteRecojo(param.getPendienteRecojo());
            venta.setCostoTotal(param.getCostoTotal());
            venta.setRazonSocial(param.getRazonSocial());
            venta.setNombres(param.getNombres());
            venta.setApellidoPaterno(param.getApellidoPaterno());
            venta.setApellidoMaterno(param.getApellidoMaterno());
            venta.setTipoVehiculo(param.getTipoVehiculo());
            venta.setPlacaVehiculo(param.getPlacaVehiculo());
            venta.setEstadoVenta(param.getEstadoVenta());
            venta.setObservacion(param.getObservacion());
            venta.setActivo(Constantes.ESTADO_ACTIVO);
            venta.setUsuarioCreacion(param.getUsuarioCreacion());
            ventaMapper.registrarVenta(venta);

            DetalleVenta detalle = new DetalleVenta();

            if (param.getRegistrarDetalle()!=null && !param.getRegistrarDetalle().isEmpty()) {
                for (RegistrarDetVentaInputDto element : param.getRegistrarDetalle()) {
                    detalle.setIdDetalleVenta(element.getIdDetalleVenta());
                    detalle.setIdVenta(venta.getIdVenta());
                    detalle.setTipoLadrillo(element.getTipoLadrillo());
                    detalle.setCantidadTotal(element.getCantidadTotal());
                    detalle.setPrecio(element.getPrecio());
                    detalle.setEstado(element.getEstado());
                    detalle.setActivo(Constantes.ESTADO_ACTIVO);
                    detalle.setUsuarioCreacion(param.getUsuarioCreacion());
                    detVentaMapper.registrarDetVenta(detalle);

                    DetalleProductoVenta detalleProducto = new DetalleProductoVenta();

                    if (element.getRegistrarProducto()!=null && !element.getRegistrarProducto().isEmpty()) {
                        for (RegistrarDetProductoVentaInputDto element2 : element.getRegistrarProducto()) {
                             /* detalleProducto.setIdDetalleProductoVenta(element2.getIdDetalleProductoVenta()); */
                             detalleProducto.setIdDetalleVenta(detalle.getIdDetalleVenta());
                             detalleProducto.setIdDetProductoTerminado(element2.getIdDetProductoTerminado());
                             detalleProducto.setActivo(Constantes.ESTADO_ACTIVO);
                             detalleProducto.setUsuarioCreacion(param.getUsuarioCreacion());
                             detProductoVentaMapper.registrarDetProductoVenta(detalleProducto);

                             DetalleProductoTerminado prodTerminado = new DetalleProductoTerminado();
                             prodTerminado.setIdDetalleProductoTerminado(element2.getIdDetProductoTerminado());
                             prodTerminado.setUtilizado(element2.getUtilizado());
                             detProductoTerminadoMapper.actualizarUtilizado(prodTerminado);
                        }
                    }
                }

            }

            VentaRecojo recojo = new VentaRecojo();
            if (param.getPendienteRecojo().equals(1)) {
                /* recojo.setIdVentaRecojo(param.getRegistrarVentaRecojo().getIdVentaRecojo()); */
                recojo.setIdVenta(venta.getIdVenta());
                recojo.setFecha(param.getRegistrarVentaRecojo().getFecha());
                recojo.setActivo(Constantes.ESTADO_ACTIVO);
                recojo.setUsuarioCreacion(param.getUsuarioCreacion());
                recojo.setObservacion(param.getRegistrarVentaRecojo().getObservacion());
                ventaRecojoMapper.registrarVentaRecojo(recojo);
            }

			Respuesta resp = new Respuesta<>();
			resp.setSuccess(venta.getResultado().equals(1)?true:false);
            resp.setMessage("Se registraron correctamente los datos");
            return resp;
		
  }

    @Override
    public Respuesta<?> listarVenta(ListarVentaInputDto param) throws Exception{
        Venta venta = new Venta();
        venta.setIdVenta(param.getIdVenta());
        venta.setFechaInicio(param.getFechaInicio());
        venta.setFechaFin(param.getFechaFin());
        venta.setEstadoVenta(param.getEstadoVenta());
        List<Venta> listaVenta = ventaMapper.listarVenta(venta);

        List<ListaVentaOutputDto> outputDto = new ArrayList<>();

        if (listaVenta!=null && !listaVenta.isEmpty()) {
            ListaVentaOutputDto vent = new ListaVentaOutputDto();
            for (Venta element : listaVenta) {
                vent = new ListaVentaOutputDto();
                vent.setIdVenta(element.getIdVenta());
                vent.setCodigo(element.getCodigo());
                vent.setFechaRegistro(element.getFechaRegistro());
                vent.setTipoDocumento(element.getTipoDocumento());
                vent.setNumeroDocumento(element.getNumeroDocumento());
                vent.setMetodoPago(element.getMetodoPago());
                vent.setPendienteRecojo(element.getPendienteRecojo());
                vent.setCostoTotal(element.getCostoTotal());
                vent.setRazonSocial(element.getRazonSocial());
                vent.setNombres(element.getNombres());
                vent.setApellidoPaterno(element.getApellidoPaterno());
                vent.setApellidoMaterno(element.getApellidoPaterno());
                vent.setTipoVehiculo(element.getTipoVehiculo());
                vent.setPlacaVehiculo(element.getPlacaVehiculo());
                vent.setEstadoVenta(element.getEstadoVenta());
                vent.setObservacion(element.getObservacion());
                vent.setActivo(element.getActivo());
                vent.setDescEstadoVenta(element.getDescEstadoVenta());
                vent.setDescripcionActivo(element.getDescripcionActivo());
                vent.setDescFechaRegistro(element.getDescFechaRegistro());
                vent.setDescTipoDocumento1(element.getDescTipoDocumento1());
                vent.setDescTipoDocumento2(element.getDescTipoDocumento2());
                
            }

            DetalleVenta detalleVenta = new DetalleVenta();
            detalleVenta.setIdVenta(param.getIdVenta());
            List<DetalleVenta> listaDetalle = detVentaMapper.listarDetalleVenta(detalleVenta);

            List<ListarDetVentaOutputDto> outputDto2 = new ArrayList<>();

            if (listaDetalle!=null && !listaDetalle.isEmpty()) {
                ListarDetVentaOutputDto det = new ListarDetVentaOutputDto();
                for (DetalleVenta element2 : listaDetalle) {
                    det = new ListarDetVentaOutputDto();
                    det.setIdDetalleVenta(element2.getIdDetalleVenta());
                    det.setIdVenta(element2.getIdVenta());
                    det.setCantidadTotal(element2.getCantidadTotal());
                    det.setPrecio(element2.getPrecio());
                    det.setTipoLadrillo(element2.getTipoLadrillo());
                    det.setEstado(element2.getEstado());
                    det.setActivo(element2.getActivo());
                    det.setDescTipoLadrillo(element2.getDescTipoLadrillo());
                    det.setDescEstadoLadrillo(element2.getDescEstadoLadrillo());
                    outputDto2.add(det);
                }

                vent.setListaDetalle(outputDto2);
                
            }

            outputDto.add(vent);
            
            Respuesta resp = new Respuesta<>();
            resp.setSuccess(true);
            resp.setMessage("Se listó correctamente");
            resp.setDato(outputDto);
            return resp;
        }else{
            
            Respuesta resp = new Respuesta<>();
            resp.setSuccess(false);
            resp.setMessage("No se encontró registros");
            return resp;
            
        
        }
    }

    @Override
	public Respuesta<?> listarDetProdTerminadoVenta(DetProdTerminadoVentaInputDto param) throws Exception {

        DetalleProductoTerminado detalleProducto = new DetalleProductoTerminado();
        detalleProducto.setIdDetalleProductoTerminado(param.getIdDetalleProductoTerminado());
        detalleProducto.setIdProductoTerminado(param.getIdProductoTerminado());
        detalleProducto.setCodigoEstado(param.getCodigoEstado());
        detalleProducto.setCodigoLadrillo(param.getCodigoLadrillo());
        List<DetalleProductoTerminado> lista = detProductoTerminadoMapper.listarDetProductoTerminadoVenta(detalleProducto);

        List<DetProdTerminadoVentaOuputDto> listaDet = new ArrayList<>();

        if (lista != null && !lista.isEmpty()) {
            DetProdTerminadoVentaOuputDto detalle = new DetProdTerminadoVentaOuputDto();
            for (DetalleProductoTerminado element : lista) {
                detalle = new DetProdTerminadoVentaOuputDto();
                detalle.setIdDetalleProductoTerminado(element.getIdDetalleProductoTerminado());
                detalle.setIdProductoTerminado(element.getIdProductoTerminado());
                detalle.setCantidadPaquete(element.getCantidadPaquete());
                detalle.setCantidadCrudo(element.getCantidadCrudo());
                detalle.setCrudo(element.getCrudo());
                detalle.setTotal(element.getTotal());
                detalle.setCodigoEstado(element.getCodigoEstado());
                detalle.setCodigoTipoLadrillo(element.getCodigoLadrillo());
                detalle.setDescripcionEstado(element.getDescripcionEstado());
                detalle.setDescripcionTipoLadrillo(element.getDescripcionTipoLadrillo());
                detalle.setCodigo(element.getCodigo());
                detalle.setUtilizado(element.getUtilizado());
                listaDet.add(detalle);
            }


            Respuesta resp = new Respuesta<>();
            resp.setSuccess(true);
            resp.setMessage("Se listó correctamente");
            resp.setDato(listaDet);
            return resp;
        }else{
            
            Respuesta resp = new Respuesta<>();
            resp.setSuccess(false);
            resp.setMessage("No se encontró registros");
            return resp;
            
        }

    }
}
