package com.prueba.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.demo.core.model.DetalleParametro;
import com.prueba.demo.core.model.Parametro;
import com.prueba.demo.core.outputDto.ListaDetParametroOutputDto;
import com.prueba.demo.mapper.ParametroMapper;
import com.prueba.demo.service.ParametroService;
import com.prueba.demo.support.dto.Respuesta;

@Service
public class ParametroServiceImpl implements ParametroService {

    @Autowired
    ParametroMapper parametroMapper;

    @Override
    public Respuesta<?> listDetParametro(String codigo) throws Exception {

        Parametro parametro = new Parametro();
        parametro.setCodigo(codigo);
        List<DetalleParametro> list = parametroMapper.listDetParametro(parametro);

        List<ListaDetParametroOutputDto> listaDetalle = new ArrayList<>();

        if (!list.isEmpty()) {
            ListaDetParametroOutputDto detalle = new ListaDetParametroOutputDto();
            for (DetalleParametro element : list) {
                detalle = new ListaDetParametroOutputDto();
                detalle.setIdDetalleParametro(element.getIdDetalleParametro());
                detalle.setIdParametro(element.getIdParametro());
                detalle.setCodigo(element.getCodigo());
                detalle.setDescripcion(element.getDescripcion());
                listaDetalle.add(detalle);
            }

            Respuesta resp = new Respuesta<>();
            resp.setSuccess(true);
            resp.setMessage("Se listó correctamente");
            resp.setDato(listaDetalle);
            return resp;

        } else {
            Respuesta resp = new Respuesta<>();
            resp.setSuccess(false);
            resp.setMessage("No se encontró registros");
            return resp;
        }

    }
}
