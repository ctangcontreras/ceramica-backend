package com.prueba.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.demo.core.inputDto.ProductoTerminadoInputDto;
import com.prueba.demo.service.ProductoTerminadoService;

@RestController
@RequestMapping("/productoTerminado")
public class ProductoTerminadoController {

    @Autowired
	private ProductoTerminadoService productoTerminadoService;
	
    private static final Logger log = LoggerFactory.getLogger(DemoController.class);

    @PostMapping("/registrarProductoTerminado")
		public ResponseEntity<?> registrarProductoTerminado(@RequestBody ProductoTerminadoInputDto input){
			try {
				return ResponseEntity.ok(productoTerminadoService.registarProductoTerminado(input));
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				return ResponseEntity.ok(e);
			}
	}

	/* @PostMapping("/eliminarQuemaProducto")
		public ResponseEntity<?> eliminarQuemaProducto(@RequestBody EliminarQuemaProductoInputDto input){
			try {
				return ResponseEntity.ok(quemaProductoService.eliminarQuemaProducto(input));
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
	} */
    
}
