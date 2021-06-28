package com.usj.minhamorada.controllers;

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
import com.usj.minhamorada.services.ApartamentoService;

@RestController
@RequestMapping("/apartamentos")
public class ApartamentoController {

	@Autowired
	ApartamentoService apartamentoService;

	@CrossOrigin
	@PostMapping
	public DTO create(@RequestBody DTO requestDTO) {
		return apartamentoService.cadastrarApartamento(requestDTO);
	}

	@CrossOrigin
	@GetMapping(value = "/{id}")
	public DTO read(@PathVariable Long id) throws Exception {
		return apartamentoService.carregarDadosApartamento(id);
	}

	@CrossOrigin
	@PutMapping(value = "/{id}")
	public DTO update(@PathVariable Long id, @RequestBody DTO requestDTO) {
		return apartamentoService.atualizarDadosApartamento(id, requestDTO);
	}

	@CrossOrigin
	@DeleteMapping(value = "/{id}")
	public DTO delete(@PathVariable Long id) throws Exception {
		return apartamentoService.deletarApartamento(id);
	}

	@CrossOrigin
	@GetMapping("/")
	public DTO findAll() throws Exception {
		return apartamentoService.listarApartamentos();
	}

}
