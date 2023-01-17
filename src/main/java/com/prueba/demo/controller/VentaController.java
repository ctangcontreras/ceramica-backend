package com.prueba.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prueba.demo.core.inputDto.DetProdTerminadoVentaInputDto;
import com.prueba.demo.core.inputDto.ListarVentaInputDto;
import com.prueba.demo.core.inputDto.RegistrarVentaInputDto;
import com.prueba.demo.service.VentaService;

@RestController
@RequestMapping("/venta")
public class VentaController {
    
    @Autowired
	private VentaService ventaService;
	
    private static final Logger log = LoggerFactory.getLogger(DemoController.class);

    @PostMapping("/registrarVenta")
		public ResponseEntity<?> registrarVenta(@RequestBody RegistrarVentaInputDto input){
			try {
				return ResponseEntity.ok(ventaService.registrarVenta(input));
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				return ResponseEntity.ok(e);
			}
	}

	@PostMapping("/listarDetProdTerminadoVenta")
		public ResponseEntity<?> listarDetProductoTerminadoVenta(@RequestBody DetProdTerminadoVentaInputDto input){
			try {
				return ResponseEntity.ok(ventaService.listarDetProdTerminadoVenta(input));
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				return ResponseEntity.ok(e);
			}
	}

	@PostMapping("/listarVenta")
		public ResponseEntity<?> listarVenta(@RequestBody ListarVentaInputDto input){
			try {
				return ResponseEntity.ok(ventaService.listarVenta(input));
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				return ResponseEntity.ok(e);
			}
	}
}
