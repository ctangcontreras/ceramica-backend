package com.prueba.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.demo.service.ParametroService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/parametro")
public class ParametroController {
    
    private static final Logger log = LoggerFactory.getLogger(DemoController.class);

	@Autowired
	private ParametroService parametroService;

    @ApiOperation(value = "lista parametro")
	@RequestMapping(value = "/getListaParametro/{codigo}", method = RequestMethod.GET)
	public ResponseEntity<Object> getListaParametro(@PathVariable("codigo") String codigo) {
		
		try {
			return ResponseEntity.ok(parametroService.listDetParametro(codigo));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.ok(e);
		}
	}
}
