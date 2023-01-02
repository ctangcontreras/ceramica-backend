package com.prueba.demo.service.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.prueba.demo.core.model.Persona;
import com.prueba.demo.mapper.PersonaMapper;
import com.prueba.demo.service.DemoService;
import com.prueba.demo.support.dto.Respuesta;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
 

@Service
public class DemoServiceImpl implements DemoService {
	private static final Logger log = LoggerFactory.getLogger(DemoServiceImpl.class);

	@Autowired
	private PersonaMapper personaMapper;


	@Override
	public Respuesta<?> getListaPersona() throws Exception {
		List<Persona> lista = personaMapper.getListaPersona();
		return new Respuesta<>(true, lista);
	}

	@Override
	public Respuesta<?> getListaPersonaProcedure() throws Exception {

		Map<String, Object> params = new HashMap<String, Object>(); 
		ResultSet rs = null;
		params.put("resultado", rs);
		personaMapper.getListaPersonaProcedure(params);

		return new Respuesta<>(true, (ArrayList<Persona>)params.get("resultado")); 
 
	}
	 
}