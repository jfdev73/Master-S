package com.codmind.orderapi.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {
	
	@GetMapping(value = "/saludo")
	public String saludo() {
		
		return "Hola Mundo";
	}

}
