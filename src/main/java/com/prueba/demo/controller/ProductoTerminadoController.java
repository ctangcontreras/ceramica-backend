package com.prueba.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.demo.core.inputDto.EliminarDetProductoTerminadoInputDto;
import com.prueba.demo.core.inputDto.EliminarProductoTerminadoInputDto;
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

	@PostMapping("/eliminarProductoTerminado")
		public ResponseEntity<?> eliminarProductoTerminado(@RequestBody EliminarProductoTerminadoInputDto input){
			try {
				return ResponseEntity.ok(productoTerminadoService.eliminarProductoTerminado(input));
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				return ResponseEntity.ok(e);
			}
	}

	@PostMapping("/listaProductoTerminado")
		public ResponseEntity<?> listaProductoTerminado(@RequestBody ProductoTerminadoInputDto input){
			try {
				return ResponseEntity.ok(productoTerminadoService.listarProductoTerminado(input));
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				return ResponseEntity.ok(e);
			}
	}

	@PostMapping("/eliminarDetProductoTerminado")
		public ResponseEntity<?> eliminarDetProductoTerminado(@RequestBody EliminarDetProductoTerminadoInputDto input){
			try {
				return ResponseEntity.ok(productoTerminadoService.eliminarDetProductoTerminado(input));
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				return ResponseEntity.ok(e);
			}
	}
    
}
