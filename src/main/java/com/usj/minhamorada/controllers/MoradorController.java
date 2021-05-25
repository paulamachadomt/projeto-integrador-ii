package com.usj.minhamorada.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usj.minhamorada.services.ApartamentoService;

@RestController
@RequestMapping("/morador")
public class MoradorController {

	@Autowired
	ApartamentoService apartamentoService;

	
		
}
