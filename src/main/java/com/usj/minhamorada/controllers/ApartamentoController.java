package com.usj.minhamorada.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.usj.minhamorada.models.dto.RequestResponseDTO;
import com.usj.minhamorada.services.ApartamentoService;

@RestController
@RequestMapping("/moradores")
public class ApartamentoController {		

		@Autowired
		ApartamentoService apartamentoService;
		
		@PostMapping
		public RequestResponseDTO create(@RequestBody RequestResponseDTO responseDTO) {
			return apartamentoService.cadastrarApartamento(responseDTO);
		}
		
		@GetMapping(value="/{id}")
		public RequestResponseDTO read(@PathVariable Long id) throws Exception {
			return apartamentoService.carregarDadosApartamento(id);			
		}
		
		@PutMapping(value="/{id}")
		public RequestResponseDTO update(@PathVariable Long id, @RequestBody RequestResponseDTO requestDTO) {
			return apartamentoService.atualizarDadosApartamento(id, requestDTO);
		}
			
		@DeleteMapping(value="/{id}")
		public RequestResponseDTO delete(@PathVariable Long id) throws Exception {
			return apartamentoService.deletarApartamento(id);
		}

}
