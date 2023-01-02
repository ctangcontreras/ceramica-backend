package com.prueba.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.demo.core.model.DetalleParametro;
import com.prueba.demo.core.model.Parametro;
import com.prueba.demo.mapper.ParametroMapper;
import com.prueba.demo.service.ParametroService;
import com.prueba.demo.support.dto.Respuesta;

@Service
public class ParametroServiceImpl implements ParametroService{
    
    @Autowired
    ParametroMapper parametroMapper;


    @Override
	public Respuesta<?> listDetParametro(String codigo) throws Exception {

		Parametro parametro = new Parametro();
        parametro.setCodigo(codigo);
        List<DetalleParametro> list = parametroMapper.listDetParametro(parametro);

    
    

        if (!list.isEmpty()) {
            Respuesta resp = new Respuesta<>();
            resp.setSuccess(true);
            resp.setMessage("Se listó correctamente");
            resp.setDato(list);
            return resp;
        } else {
            Respuesta resp = new Respuesta<>();
            resp.setSuccess(false);
            resp.setMessage("No se encontró registros");
            return resp;
        }
 
	}
}
