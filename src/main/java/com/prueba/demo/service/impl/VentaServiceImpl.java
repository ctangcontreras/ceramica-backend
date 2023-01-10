package com.prueba.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prueba.demo.core.inputDto.RegistrarDetProductoVentaInputDto;
import com.prueba.demo.core.inputDto.RegistrarDetVentaInputDto;
import com.prueba.demo.core.inputDto.RegistrarVentaInputDto;
import com.prueba.demo.core.model.DetalleProductoVenta;
import com.prueba.demo.core.model.DetalleVenta;
import com.prueba.demo.core.model.Venta;
import com.prueba.demo.mapper.DetProductoVentaMapper;
import com.prueba.demo.mapper.DetVentaMapper;
import com.prueba.demo.mapper.VentaMapper;
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

    @Override
    @Transactional
	public Respuesta<?> registrarVenta(RegistrarVentaInputDto param) throws Exception {

            Venta venta = new Venta();
            venta.setIdVenta(param.getIdVenta());
            venta.setFechaRegistro(param.getFechaRegistro());
            venta.setTipoDocumento(param.getTipoDocumento());
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
                             detalleProducto.setIdDetalleProductoVenta(element2.getIdDetalleProductoVenta());
                             detalleProducto.setIdDetalleVenta(detalle.getIdDetalleVenta());
                             detalleProducto.setIdDetProductoTerminado(element2.getIdDetProductoTerminado());
                             detalle.setActivo(Constantes.ESTADO_ACTIVO);
                             detProductoVentaMapper.registrarDetProductoVenta(detalleProducto);
                        }
                    }
                }

            }

			Respuesta resp = new Respuesta<>();
			resp.setSuccess(venta.getResultado().equals(1)?true:false);
            resp.setMessage("Se registraron correctamente los datos");
            return resp;
		
  }
}
