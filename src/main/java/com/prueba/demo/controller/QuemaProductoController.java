package com.prueba.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.demo.core.inputDto.QuemaProductoInputDto;
import com.prueba.demo.service.QuemaProductoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/quemaProducto")
public class QuemaProductoController {

    @Autowired
	private QuemaProductoService quemaProductoService;
	
    private static final Logger log = LoggerFactory.getLogger(DemoController.class);

    @PostMapping("/registrarQuemaProducto")
		public ResponseEntity<?> registrarQuemaProducto(@RequestBody QuemaProductoInputDto input){
			try {
				return ResponseEntity.ok(quemaProductoService.registarQuemaProducto(input));
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				return ResponseEntity.ok(e);
			}
	}

	@PostMapping("/listaQuemaProducto")
		public ResponseEntity<?> getListaUsuario(@RequestBody QuemaProductoInputDto input){
			try {
				return ResponseEntity.ok(quemaProductoService.listarQuemaProducto(input));
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				return ResponseEntity.ok(e);
			}
	}
    
}
