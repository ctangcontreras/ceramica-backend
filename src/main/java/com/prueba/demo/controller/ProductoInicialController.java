package com.prueba.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.demo.core.inputDto.ProductoInicialInputDto;
import com.prueba.demo.service.ProductoInicialService;

import io.swagger.annotations.Api;


@RestController
@RequestMapping("/productoInicial")
@Api(value = "HelloWorld Resource", description = "shows hello world")
public class ProductoInicialController {

	private static final Logger log = LoggerFactory.getLogger(DemoController.class);

	@Autowired
	private ProductoInicialService productoInicialService;
	
    @PostMapping("/getRegistrarProductoInicial")
		public ResponseEntity<?> getRegistrarProductoInicial(@RequestBody ProductoInicialInputDto productoInicial){
			try {
				return ResponseEntity.ok(productoInicialService.getRegistrarProductoInicial(productoInicial));
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				return ResponseEntity.ok(e);
			}
		}

		@PostMapping("/getListaProductoInicial")
		public ResponseEntity<?> getListaUsuario(@RequestBody ProductoInicialInputDto productoInicialInputDto){
			try {
				return ResponseEntity.ok(productoInicialService.getListarProductoInicial(productoInicialInputDto));
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				return ResponseEntity.ok(e);
			}
		}
}