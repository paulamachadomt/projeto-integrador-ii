package com.usj.minhamorada.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usj.minhamorada.models.dto.DTO;
import com.usj.minhamorada.services.MoradorService;

@RestController
@RequestMapping("/moradores")
public class MoradorController {

	@Autowired
	MoradorService moradorService;
	
	@CrossOrigin
	@PostMapping
	public DTO create(@RequestBody DTO request) {
		return moradorService.cadastrarMorador(request);
	}
	
	@CrossOrigin
	@GetMapping(value="/{id}")
	public DTO read(@PathVariable Long id) throws Exception {
		return moradorService.carregarDadosMorador(id);
	}
	
	@CrossOrigin
	@PutMapping(value="/{id}")
	public DTO update(@PathVariable Long id, @RequestBody DTO request) throws Exception {
		return moradorService.atualizarDadosMorador(id, request);
	}
	
	@CrossOrigin
	@DeleteMapping(value="/{id}")
	public DTO delete(@PathVariable Long id) throws Exception {
		return moradorService.deletarMorador(id);	
	}
	
	@CrossOrigin
	@GetMapping(value="/")
	public DTO findAll() throws Exception {
		return moradorService.listarMoradores();
	}
}
