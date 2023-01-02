package com.prueba.demo.controller;

import com.prueba.demo.service.DemoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/demo")
@Api(value = "HelloWorld Resource", description = "shows hello world")
public class DemoController {

	private static final Logger log = LoggerFactory.getLogger(DemoController.class);

	@Autowired
	private DemoService demoService;
	
	@ApiOperation(value = "Returns Hello World")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 100, message = "100 is the message"),
                    @ApiResponse(code = 200, message = "Successful Hello World")
            }
    )	
	@RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
	public HttpEntity<String> findAlls() {
		return ResponseEntity.ok("hola");
	}
 
	@ApiOperation(value = "lista persona")
	@RequestMapping(value = "/getListaPersona", method = RequestMethod.GET)
	public ResponseEntity<Object> getListaPersona() {
		
		try {
			return ResponseEntity.ok(demoService.getListaPersona());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.ok(e);
		}
	}

	@ApiOperation(value = "lista persona procedure")
	@RequestMapping(value = "/getListaPersonaProcedure", method = RequestMethod.GET)
	public ResponseEntity<Object> getListaPersonaProcedure() {
		
		try {
			return ResponseEntity.ok(demoService.getListaPersonaProcedure());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.ok(e);
		}
	}

}
