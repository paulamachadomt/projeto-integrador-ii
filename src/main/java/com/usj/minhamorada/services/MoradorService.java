package com.usj.minhamorada.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usj.minhamorada.models.Apartamento;
import com.usj.minhamorada.models.Morador;
import com.usj.minhamorada.models.dto.DTO;
import com.usj.minhamorada.repositories.MoradorRepository;

@Service
public class MoradorService {
	
	@Autowired
	MoradorRepository moradorRepository;
	
	@Autowired
	ApartamentoService apartamentoService;
	
	private String statuscode = "200";
	
	public DTO cadastrarMorador(DTO request) {
		try {
			Morador morador = request.getMorador();			
			verificaCadastroCamposObrigatorios(morador);
			Apartamento apartamento = apartamentoService.readApartamentoById(request.getMorador().getApartamento().getId());
			isApartamentoVazio(null, apartamento);
			apartamento.setMorador(morador);
			morador.setApartamento(apartamento);			
			morador = moradorRepository.save(morador);			
			return response(morador, statuscode, "Morador cadastrado com sucesso!");
		} catch (Exception e) {
			return response(statuscode, e.getMessage());
		}		
	}
	
	public DTO carregarDadosMorador(Long id) {
		try {
			Morador morador = readMoradorById(id);
			return response(morador, statuscode, "Morador encontrado com sucesso!");
		} catch (Exception e) {
			return response(statuscode, e.getMessage());
		}		
	}
		
	public DTO atualizarDadosMorador(Long id, DTO request) {
		try {
			Morador morador = readMoradorById(id);
			Apartamento apartamento = apartamentoService.readApartamentoById(request.getMorador().getApartamento().getId());
			isApartamentoVazio(id, apartamento);
			morador.setNome(request.getMorador().getNome());
			morador.setTelefone(request.getMorador().getTelefone());
			morador.setEmail(request.getMorador().getEmail());
			morador.setDataNascimento(request.getMorador().getDataNascimento());
			morador.setApartamento(apartamento);
			moradorRepository.save(morador);
			return response(morador, statuscode, "Cadastrado atualizado com sucesso!");			
		} catch (Exception e) {
			return response(statuscode, e.getMessage());
		}		
	}
		
	public DTO deletarMorador(Long id) {
		try {
			Morador morador = readMoradorById(id);
			moradorRepository.deleteById(id);
			return response(statuscode, "Morador deletado com sucesso!");			
		} catch (Exception e) {
			return response(statuscode, e.getMessage());
		}		
	}
	
	public DTO listarMoradores() {
		try {
			List<Morador> listaMoradores = new ArrayList<Morador>();
			listaMoradores = moradorRepository.findAll();
			return response(listaMoradores, statuscode, "Lista de moradores encontrada com sucesso!");			
		} catch (Exception e) {
			return response(statuscode, e.getMessage());
		}
	}
	
	
	
	
	//Verifica se o apartamento está vazio e pode receber um novo morador
	//Verifica se o apartamento do request de update é do próprio morador que solicitou a atualização
	boolean isApartamentoVazio(Long id, Apartamento apartamento) throws Exception {
		if ((apartamento.getMorador() == null) || (apartamento.getMorador().getId() == id))  {
			return true;
		}
		else {
			throw new Exception("Apartamento já possui morador!");
		}
	}
			
	Morador readMoradorById(Long id) throws Exception {
		try {
			return moradorRepository.findById(id).get();
		} catch (Exception e) {
			statuscode = "404";
			throw new Exception("Verifique ID do morador!");
		}
	}
	
	boolean verificaCadastroCamposObrigatorios(Morador morador) throws Exception {
		if(morador.isEmptyCPF()) {
			statuscode = "400";
			throw new Exception("Verifique campos obrigatórios para cadastrar um morador!");
		} else if (morador.isEmptyNome()) {
			statuscode = "400";
			throw new Exception("Verifique campos obrigatórios para cadastrar um morador!");
		}else if (morador.isEmptyTelefone()) {
			statuscode = "400";
			throw new Exception("Verifique campos obrigatórios para cadastrar um morador!");
		}
		return true;
	}
		
	DTO response(Morador morador, String statuscode, String mensagem){
		return DTO.builder().morador(morador).statusCode(statuscode).mensagem(mensagem).build();
	}
	
	DTO response(String statuscode, String mensagem){
		return DTO.builder().statusCode(statuscode).mensagem(mensagem).build();
	}

	DTO response(List<Morador> listaMoradores, String statuscode, String mensagem){
		return DTO.builder().listaMoradores(listaMoradores).statusCode(statuscode).mensagem(mensagem).build();
	}
	

}
