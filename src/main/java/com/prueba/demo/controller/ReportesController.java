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
import com.prueba.demo.core.inputDto.ProductoTerminadoInputDto;
import com.prueba.demo.service.ReportesService;

import io.swagger.annotations.Api;


@RestController
@RequestMapping("/reportes")
@Api(value = "HelloWorld Resource", description = "shows hello world")
public class ReportesController {

	private static final Logger log = LoggerFactory.getLogger(DemoController.class);

	@Autowired
	private ReportesService reportesService;
	
		@PostMapping("/generarReporteExcelProductoInicial")
		public ResponseEntity<?> listarProductoInicialExcel(@RequestBody ProductoInicialInputDto productoInicialInputDto){
			try {
				return ResponseEntity.ok(reportesService.listarProductoInicialExcel(productoInicialInputDto));
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				return ResponseEntity.ok(e);
			}
		}

		@PostMapping("/generarReporteExcelProductoTerminado")
		public ResponseEntity<?> listarProductoTerminadoExcel(@RequestBody ProductoTerminadoInputDto productoTerminadoInputDto){
			try {
				return ResponseEntity.ok(reportesService.listarProductoTerminadoExcel(productoTerminadoInputDto));
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				return ResponseEntity.ok(e);
			}
		}

	
}