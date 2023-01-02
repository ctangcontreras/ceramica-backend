package com.prueba.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.demo.core.inputDto.UsuarioInputDto;
import com.prueba.demo.service.UsuarioService;

import io.swagger.annotations.Api;


@RestController
@RequestMapping("/usuario")
@Api(value = "HelloWorld Resource", description = "shows hello world")
public class UsuarioController {

	private static final Logger log = LoggerFactory.getLogger(DemoController.class);

	@Autowired
	private UsuarioService usuarioService;
	
    @PostMapping("/getListaUsuario")
		public ResponseEntity<?> getListaUsuario(@RequestBody UsuarioInputDto usuario){
			try {
				return ResponseEntity.ok(usuarioService.getListaUsuario(usuario));
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				return ResponseEntity.ok(e);
			}
		}
}